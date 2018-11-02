package ch.hsr.adv.lib.tree.logic.collectiontree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.TreeBuilderBase;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.MultipleParentsException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Builder Strategy for CollectionTreeModule. It builds a ModuleGroup
 * containing the module data. Can only handle collection trees!
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_COLLECTION_TREE)
public class CollectionTreeBuilder extends TreeBuilderBase implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            CollectionTreeBuilder.class);
    private static final long DEFAULT_PARENT_RANK = -1;

    private Set<? extends ADVGeneralTreeNode<?>> moduleNodes;
    private Map<ADVGeneralTreeNode<?>,
            NodeInformationHolder<ADVGeneralTreeNode<?>>> nodeInformation;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (!ConstantsTree.MODULE_NAME_COLLECTION_TREE
                .equals(advModule.getModuleName())) {
            return null;
        }
        logger.info("start building ModuleGroup from CollectionTreeModule");
        CollectionTreeModule<?> module = (CollectionTreeModule<?>) advModule;
        initializeBuild(module);

        Set<ADVGeneralTreeNode<?>> visitedNodes = new HashSet<>();
        for (ADVGeneralTreeNode<?> node : moduleNodes) {
            buildNode(node, DEFAULT_PARENT_RANK, visitedNodes);
        }

        checkCyclicNodes();

        ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());
        addElementsToModuleGroup(moduleGroup);
        return moduleGroup;
    }

    private void initializeBuild(CollectionTreeModule<?> module) {
        nodeInformation = new HashMap<>();
        moduleNodes = module.getNodes();
    }

    private void buildNode(ADVGeneralTreeNode<?> node, long parentRank,
                           Set<ADVGeneralTreeNode<?>> visitedNodes) {
        if (visitedNodes.contains(node)) {
            NodeInformationHolder<ADVGeneralTreeNode<?>> info =
                    nodeInformation.get(node);
            if (parentRank != DEFAULT_PARENT_RANK) {
                if (info.getParentRank() == DEFAULT_PARENT_RANK) {
                    info.setParentRank(parentRank);
                } else {
                    String message = "Node (" + node.getContent().toString()
                            + ") has multiple parents";
                    throw new MultipleParentsException(message);
                }
            }
        } else if (moduleNodes.contains(node)) {
            long rank = visitedNodes.size();
            nodeInformation.put(node,
                    new NodeInformationHolder<>(parentRank, rank, node));
            visitedNodes.add(node);
            for (ADVGeneralTreeNode<?> child : node.getChildren()) {
                buildNode(child, rank, visitedNodes);
            }
        }
    }

    private void checkCyclicNodes() {
        Set<ADVTreeNode<?>> visitedNodes = new HashSet<>();
        for (ADVGeneralTreeNode<?> root : getRoots()) {
            checkCyclicNode(root, visitedNodes);
        }

        if (visitedNodes.size() != moduleNodes.size()) {
            throw new CyclicNodeException("At least one tree has a cycle");
        }
    }

    private void checkCyclicNode(ADVGeneralTreeNode<?> node,
                                 Set<ADVTreeNode<?>> visitedNodes) {
        if (moduleNodes.contains(node)) {
            checkCyclicNode(visitedNodes,
                    nodeInformation.get(node).getParentRank(), node);
            for (ADVGeneralTreeNode<?> child : node.getChildren()) {
                checkCyclicNode(child, visitedNodes);
            }
        }
    }

    private List<ADVGeneralTreeNode<?>> getRoots() {
        List<ADVGeneralTreeNode<?>> roots = new ArrayList<>();
        for (Map.Entry<ADVGeneralTreeNode<?>,
                NodeInformationHolder<ADVGeneralTreeNode<?>>> infoEntry
                : nodeInformation.entrySet()) {
            if (infoEntry.getValue().getParentRank() == DEFAULT_PARENT_RANK) {
                roots.add(infoEntry.getKey());
            }
        }
        return roots;
    }

    private void addElementsToModuleGroup(ModuleGroup moduleGroup) {
        for (NodeInformationHolder<ADVGeneralTreeNode<?>> info
                : nodeInformation.values()) {
            addElementToModuleGroup(moduleGroup, info);
        }
    }

    private void addElementToModuleGroup(
            ModuleGroup moduleGroup,
            NodeInformationHolder<ADVGeneralTreeNode<?>> information) {
        moduleGroup.addElement(new TreeNodeElement(
                information.getChildNode(),
                information.getChildRank()));

        if (information.getParentRank() != DEFAULT_PARENT_RANK) {
            moduleGroup.addRelation(new TreeNodeRelation(
                    information.getParentRank(),
                    information.getChildRank(),
                    information.getChildNode().getStyle()));
        }
    }
}
