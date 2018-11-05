package ch.hsr.adv.lib.tree.logic.generaltree;

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
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.Set;

/**
 * Builder Strategy for GeneralTreeModule. It builds a ModuleGroup
 * containing the module data. Can handle trees with n-children
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_GENERAL_TREE)
public class GeneralTreeBuilder extends TreeBuilderBase implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            GeneralTreeBuilder.class);

    private static final long START_ID = 1;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_GENERAL_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building Modulegroup from GeneralTreeModule");

            GeneralTreeModule module = (GeneralTreeModule) advModule;
            ADVGeneralTreeNode<?> root = module.getRoot();
            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());

            if (root != null) {
                buildNodes(root, moduleGroup);
            } else {
                logger.error("Root Node from the GeneralTreeModule is null");
                throw new RootUnspecifiedException("The root node must not be"
                        + " null");
            }

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildNodes(ADVGeneralTreeNode<?> root,
                            ModuleGroup moduleGroup) {
        Set<ADVTreeNode<?>> visitedNodes = new HashSet<>();
        logger.debug("Root Node: " + root.toString());

        visitedNodes.add(root);
        moduleGroup.addElement(new TreeNodeElement(root, START_ID));

        buildChildren(root, START_ID, moduleGroup, visitedNodes);
    }

    private long buildChild(ModuleGroup moduleGroup,
                            NodeInformationHolder<ADVGeneralTreeNode<?>>
                                    nodeInformation,
                            Set<ADVTreeNode<?>> visitedNodes) {
        if (nodeInformation.getChildNode() != null) {
            logger.debug("ChildNode: " + nodeInformation.getChildNode());

            checkCyclicNode(visitedNodes, nodeInformation.getParentRank(),
                    nodeInformation.getChildNode());

            moduleGroup.addElement(new TreeNodeElement(
                    nodeInformation.getChildNode(),
                    nodeInformation.getChildRank()));

            moduleGroup.addRelation(new TreeNodeRelation(
                    nodeInformation.getParentRank(),
                    nodeInformation.getChildRank(),
                    nodeInformation.getChildNode().getStyle()));

            return buildChildren(nodeInformation.getChildNode(),
                    nodeInformation.getChildRank(), moduleGroup, visitedNodes);
        } else {
            logger.error("The childNode with ID "
                    + nodeInformation.getChildRank() + " and parentID "
                    + nodeInformation.getParentRank() + " is null");
            throw new IllegalArgumentException("The childNode with ID "
                    + nodeInformation.getChildRank() + " and parentID "
                    + nodeInformation.getParentRank() + " must not be "
                    + "null!");
        }

    }

    private long buildChildren(ADVGeneralTreeNode<?> node, long nodeId,
                               ModuleGroup moduleGroup,
                               Set<ADVTreeNode<?>> visitedNodes) {
        long lastChildId = nodeId;

        if (node.getChildren() != null) {
            for (ADVGeneralTreeNode<?> childOfChild : node.getChildren()) {
                lastChildId = buildChild(moduleGroup,
                        new NodeInformationHolder<>(nodeId, lastChildId + 1,
                                childOfChild), visitedNodes);
            }
        }

        return lastChildId;
    }
}
