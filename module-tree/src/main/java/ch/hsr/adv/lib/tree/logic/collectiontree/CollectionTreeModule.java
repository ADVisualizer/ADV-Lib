package ch.hsr.adv.lib.tree.logic.collectiontree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;

import java.util.*;

/**
 * Encapsulates module meta data and Collection-Tree data to be sent to the
 * ADV UI.
 *
 * @param <T> type of node content
 */
public class CollectionTreeModule<T> extends TreeModuleBase {

    private LinkedHashSet<ADVGeneralTreeNode<T>> nodeSet;

    public CollectionTreeModule(String sessionName) {
        super(sessionName);
        nodeSet = new LinkedHashSet<>();
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_COLLECTION_TREE;
    }

    /**
     * adds the node to the module if the node is not already included
     *
     * @param node node to add
     */
    public void add(ADVGeneralTreeNode<T> node) {
        if (node != null) {
            nodeSet.add(node);
        } else {
            throw new IllegalArgumentException("node must not be null");
        }
    }

    /**
     * adds all nodes to the module that are not already included
     *
     * @param nodes nodes to add
     */
    public void add(Collection<? extends ADVGeneralTreeNode<T>> nodes) {
        if (nodes != null) {
            nodes.forEach(this::add);
        } else {
            throw new IllegalArgumentException("nodes-collection must not be "
                    + "null");
        }
    }

    /**
     * adds all nodes to the module that are not already included
     *
     * @param nodes nodes to add
     */
    public void add(ADVGeneralTreeNode<T>[] nodes) {
        if (nodes != null) {
            Arrays.asList(nodes).forEach(this::add);
        } else {
            throw new IllegalArgumentException("nodes-array must not be null");
        }
    }

    /**
     * removes the node from the module if it is contained
     *
     * @param node node to remove
     */
    public void remove(ADVGeneralTreeNode<T> node) {
        nodeSet.remove(node);
    }

    /**
     * removes the nodes from the module that are contained in it
     *
     * @param nodes nodes to remove
     */
    public void remove(List<? extends ADVGeneralTreeNode<T>> nodes) {
        nodeSet.removeAll(nodes);
    }

    /**
     * removes the nodes from the module that are contained in it
     *
     * @param nodes nodes to remove
     */
    public void remove(ADVGeneralTreeNode<T>[] nodes) {
        nodeSet.removeAll(Arrays.asList(nodes));
    }

    protected Set<ADVGeneralTreeNode<T>> getNodes() {
        return nodeSet;
    }
}
