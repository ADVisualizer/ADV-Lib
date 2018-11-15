package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * A base class for the NodeTreeBuilder classes to encapsulate shared
 * functionality
 */
public abstract class TreeBuilderBase {

    private static final Logger logger = LoggerFactory.getLogger(
            TreeBuilderBase.class);

    protected static final long DEFAULT_PARENT_RANK = -1;

    /**
     * tests whether cycles exist in the current node tree
     *
     * @param visitedNodes all current visited nodes of the tree
     * @param parentRank   the rank/id of the parent node to help finding errors
     * @param childNode    new node to check whether it was visited
     */
    protected void checkCyclicNode(Set<ADVTreeNode<?>> visitedNodes,
                                   long parentRank,
                                   ADVTreeNode<?> childNode) {
        if (!visitedNodes.add(childNode)) {
            String errorMessage =
                    "the child (" + childNode.getContent().toString()
                            + ") of Parent with Rank " + parentRank
                            + " is already a node in the tree";
            logger.error(errorMessage);
            throw new CyclicNodeException(errorMessage);
        }
    }

    /**
     * adds tree-node and its parent-child-relation to the ModuleGroup
     *
     * @param moduleGroup     module that receives the node
     * @param nodeInformation information about the node
     */
    protected void addNodeToModuleGroup(
            ModuleGroup moduleGroup,
            NodeInformationHolder<? extends ADVTreeNode<?>> nodeInformation) {
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
