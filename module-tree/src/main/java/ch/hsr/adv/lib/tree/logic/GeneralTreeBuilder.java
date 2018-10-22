package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
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
public class GeneralTreeBuilder implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            BinaryTreeBuilder.class);

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
        Set<ADVGeneralTreeNode<?>> visitedNodes = new HashSet<>();
        logger.debug("Root Node: " + root.toString());

        visitedNodes.add(root);
        moduleGroup.addElement(new TreeNodeElement(root, START_ID));

        buildChildren(root, START_ID, moduleGroup, visitedNodes);
    }

    private long buildChild(ADVGeneralTreeNode<?> childNode, long parentId,
                            long childId, ModuleGroup moduleGroup,
                            Set<ADVGeneralTreeNode<?>> visitedNodes) {
        if (childNode != null) {
            logger.debug("ChildNode: " + childNode.toString());

            checkCyclicNode(visitedNodes, parentId, childNode);

            moduleGroup.addElement(new TreeNodeElement(childNode, childId));

            moduleGroup.addRelation(new TreeNodeRelation(parentId, childId,
                    childNode.getStyle()));

            return buildChildren(childNode, childId, moduleGroup, visitedNodes);
        } else {
            logger.error("The childNode with ID " + childId + " and parentID "
                    + parentId + " is null");
            throw new IllegalArgumentException("The childNode with ID "
                    + childId + " and parentID " + parentId + " must not be "
                    + "null!");
        }

    }

    private long buildChildren(ADVGeneralTreeNode<?> node, long nodeId,
                               ModuleGroup moduleGroup,
                               Set<ADVGeneralTreeNode<?>> visitedNodes) {
        long lastChildId = nodeId;

        if (node.getChildren() != null) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                ADVGeneralTreeNode<?> childOfChild =
                        node.getChildren().get(i);
                lastChildId = buildChild(childOfChild, nodeId,
                        lastChildId + 1, moduleGroup, visitedNodes);
            }
        }

        return lastChildId;
    }

    private void checkCyclicNode(Set<ADVGeneralTreeNode<?>> visitedNodes,
                                 long parentRank,
                                 ADVGeneralTreeNode<?> childNode) {
        if (visitedNodes.contains(childNode)) {
            String errorMessage = "the child (" + childNode.toString()
                    + " of Parent with Rank " + parentRank + "is already a "
                    + "node in the tree";
            logger.error(errorMessage);
            throw new CyclicNodeException(errorMessage);
        } else {
            visitedNodes.add(childNode);
        }
    }
}
