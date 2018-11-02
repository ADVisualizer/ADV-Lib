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

    private Set<ADVGeneralTreeNode<T>> nodeSet;

    public CollectionTreeModule(String sessionName) {
        super(sessionName);
        nodeSet = new HashSet<>();
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
        nodeSet.add(node);
    }

    /**
     * adds all nodes to the module that are not already included
     *
     * @param nodes nodes to add
     */
    public void add(Collection<? extends ADVGeneralTreeNode<T>> nodes) {
        nodeSet.addAll(nodes);
    }

    /**
     * adds all nodes to the module that are not already included
     *
     * @param nodes nodes to add
     */
    public void add(ADVGeneralTreeNode<T>[] nodes) {
        nodeSet.addAll(Arrays.asList(nodes));
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
