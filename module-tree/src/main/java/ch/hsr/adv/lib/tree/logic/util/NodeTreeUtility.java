package ch.hsr.adv.lib.tree.logic.util;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Utility class with methods for all node trees
 */
public final class NodeTreeUtility {

    private static final long DEFAULT_ID = -1;

    /**
     * basic method to traverse a general tree
     *
     * @param root        the root
     * @param nodeHandler handler to tell what should happen when a node is
     *                    visited
     * @param <T>         type of the content of a node
     */
    public static <T> void traverseTree(ADVGeneralTreeNode<T> root,
                                        GeneralTraversalFunction<T>
                                                nodeHandler) {
        buildTree(root, DEFAULT_ID, null, nodeHandler);
    }

    /**
     * extended variant of a general tree traversal
     *
     * @param root        the root
     * @param rootId      the id of the root
     * @param moduleGroup the data sent to the ui
     * @param nodeHandler handler to tell what should happen when a node is
     *                    visited
     * @param <T>         type of the content of a node
     */
    public static <T> void buildTree(ADVGeneralTreeNode<T> root,
                                     long rootId, ModuleGroup moduleGroup,
                                     GeneralTraversalFunction<T> nodeHandler) {
        buildTree(root, rootId, moduleGroup,
                (childNode) -> true, nodeHandler);
    }

    /**
     * extended variant of a general tree traversal with the possibility to stop
     * following a branch of the tree
     *
     * @param root        the root
     * @param rootId      the id of the root
     * @param moduleGroup the data sent to the ui
     * @param outer       predicate with the information when to stop
     *                    following a branch
     * @param nodeHandler handler to tell what should happen when a node is
     *                    visited
     * @param <T>         type of the content of a node
     * @return the last child id of the tree
     */
    public static <T> long buildTree(ADVGeneralTreeNode<T> root,
                                     long rootId,
                                     ModuleGroup moduleGroup,
                                     Predicate<ADVGeneralTreeNode<T>> outer,
                                     GeneralTraversalFunction<T> nodeHandler) {
        Set<ADVTreeNode<T>> visitedNodes = new HashSet<>();
        return buildTree(root, rootId, DEFAULT_ID, moduleGroup, visitedNodes,
                outer, nodeHandler);
    }

    private static <T> long buildTree(ADVGeneralTreeNode<T> child,
                                      long childId, long parentId,
                                      ModuleGroup moduleGroup,
                                      Set<ADVTreeNode<T>> visitedNodes,
                                      Predicate<ADVGeneralTreeNode<T>> outer,
                                      GeneralTraversalFunction<T> nodeHandler) {
        long nextChildRank = childId;
        if (outer.test(child)) {
            checkCyclicNode(visitedNodes, parentId, child);

            nodeHandler.apply(moduleGroup, child, parentId, childId);
            nextChildRank++;
            if (child.getChildren() != null) {
                for (ADVGeneralTreeNode<T> childOfChild : child.getChildren()) {
                    nextChildRank = buildTree(childOfChild, nextChildRank,
                            childId, moduleGroup, visitedNodes, outer,
                            nodeHandler);
                }
            }
        }
        return nextChildRank;
    }

    /**
     * tests whether cycles exist in the current node tree
     *
     * @param visitedNodes all current visited nodes of the tree
     * @param parentRank   the rank/id of the parent node to help finding errors
     * @param childNode    new node to check whether it was visited
     */
    private static <T> void checkCyclicNode(Set<ADVTreeNode<T>> visitedNodes,
                                            long parentRank,
                                            ADVTreeNode<T> childNode) {
        if (!visitedNodes.add(childNode)) {
            String errorMessage =
                    "the child (" + childNode.getContent().toString()
                            + ") of Parent with Rank " + parentRank
                            + " is already a node in the tree";
            throw new CyclicNodeException(errorMessage);
        }
    }
}
