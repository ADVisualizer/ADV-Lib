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
    private static final long START_RANK = 1;

    private Set<? extends ADVGeneralTreeNode<?>> moduleNodes;
    private List<NodeInformationHolder<ADVGeneralTreeNode<?>>>
            nodeInformationList;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_COLLECTION_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building ModuleGroup from CollectionTreeModule");
            CollectionTreeModule<?> module =
                    (CollectionTreeModule<?>) advModule;
            initializeBuild(module);

            List<ADVGeneralTreeNode<?>> roots = findRoots();

            long currentRank = START_RANK;
            Set<ADVTreeNode<?>> visitedNodes = new HashSet<>();
            for (ADVGeneralTreeNode<?> root : roots) {
                currentRank = buildTree(root, currentRank,
                        DEFAULT_PARENT_RANK, visitedNodes);
            }

            if (moduleNodes.size() != nodeInformationList.size()) {
                String errorMessage = "Not all roots correctly "
                        + "identified. Please check for cycles";
                logger.error(errorMessage);
                throw new CyclicNodeException(errorMessage);
            }

            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());
            addElementsToModuleGroup(moduleGroup);
            return moduleGroup;
        } else {
            return null;
        }
    }

    private void initializeBuild(CollectionTreeModule<?> module) {
        nodeInformationList = new ArrayList<>();
        moduleNodes = module.getNodes();
    }

    private List<ADVGeneralTreeNode<?>> findRoots() {
        Set<ADVGeneralTreeNode<?>> children = new HashSet<>();
        for (ADVGeneralTreeNode<?> moduleNode : moduleNodes) {
            for (ADVGeneralTreeNode<?> child : moduleNode.getChildren()) {
                if (moduleNodes.contains(child)) {
                    if (!children.add(child)) {
                        String errorMessage = "Node (" + child.getContent()
                                .toString() + ") has multiple parents";
                        logger.error(errorMessage);
                        throw new MultipleParentsException(errorMessage);
                    }
                }
            }
        }
        List<ADVGeneralTreeNode<?>> roots = new ArrayList<>();
        for (ADVGeneralTreeNode<?> moduleNode : moduleNodes) {
            if (!children.contains(moduleNode)) {
                roots.add(moduleNode);
            }
        }
        return roots;
    }

    private long buildTree(ADVGeneralTreeNode<?> child, long childRank,
                           long parentRank, Set<ADVTreeNode<?>> visitedNodes) {
        long nextChildRank = childRank;
        if (moduleNodes.contains(child)) {
            checkCyclicNode(visitedNodes, parentRank, child);

            nodeInformationList.add(
                    new NodeInformationHolder<>(parentRank, childRank, child));
            nextChildRank++;
            for (ADVGeneralTreeNode<?> childOfChild : child.getChildren()) {
                nextChildRank = buildTree(childOfChild, nextChildRank,
                        childRank, visitedNodes);
            }
        }
        return nextChildRank;
    }

    private void addElementsToModuleGroup(ModuleGroup moduleGroup) {
        for (NodeInformationHolder<ADVGeneralTreeNode<?>> nodeInformation
                : nodeInformationList) {
            addElementToModuleGroup(moduleGroup, nodeInformation);
        }
    }

    private void addElementToModuleGroup(
            ModuleGroup moduleGroup,
            NodeInformationHolder<ADVGeneralTreeNode<?>> nodeInformation) {
        moduleGroup.addElement(new TreeNodeElement(
                nodeInformation.getChildNode(),
                nodeInformation.getChildRank()));

        if (nodeInformation.getParentRank() != DEFAULT_PARENT_RANK) {
            moduleGroup.addRelation(new TreeNodeRelation(
                    nodeInformation.getParentRank(),
                    nodeInformation.getChildRank(),
                    nodeInformation.getChildNode().getStyle()));
        }
    }
}
