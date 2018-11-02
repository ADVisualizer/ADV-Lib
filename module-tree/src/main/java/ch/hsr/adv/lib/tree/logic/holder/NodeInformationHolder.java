package ch.hsr.adv.lib.tree.logic.holder;

import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;

/**
 * The NodeInformationHolder contains information which are important for the
 * tree traversal in the BinaryTreeBuilder class
 * @param <T> node-type
 */
public class NodeInformationHolder<T extends ADVTreeNode<?>> {
    private long parentRank;
    private long childRank;
    private T childNode;

    public NodeInformationHolder(long parentRank, long childRank,
                                 T childNode) {
        this.parentRank = parentRank;
        this.childRank = childRank;
        this.childNode = childNode;
    }

    public void setParentRank(long parentRank) {
        this.parentRank = parentRank;
    }

    public long getParentRank() {
        return parentRank;
    }

    public long getChildRank() {
        return childRank;
    }

    public T getChildNode() {
        return childNode;
    }
}
