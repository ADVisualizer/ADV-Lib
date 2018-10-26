package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * A base class for the NodeTreeBuilder classes to encapsulate shared
 * functionality
 */
abstract class TreeBuilderBase {
    private static final Logger logger = LoggerFactory.getLogger(
            TreeBuilderBase.class);

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
            String errorMessage = "the child (" + childNode.toString()
                    + " of Parent with Rank " + parentRank + "is already a "
                    + "node in the tree";
            logger.error(errorMessage);
            throw new CyclicNodeException(errorMessage);
        }
    }
}
