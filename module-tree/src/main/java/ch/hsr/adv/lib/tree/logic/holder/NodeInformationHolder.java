package ch.hsr.adv.lib.tree.logic.holder;

import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;

/**
 * The NodeInformationHolder contains information which are important for the
 * tree traversal in the BinaryTreeBuilder class
 */
public class NodeInformationHolder {
    private long parentRank;
    private long childRank;
    private ADVBinaryTreeNode<?> childNode;

    public NodeInformationHolder(long parentRank, long childRank,
                                 ADVBinaryTreeNode<?> childNode) {
        this.parentRank = parentRank;
        this.childRank = childRank;
        this.childNode = childNode;
    }

    public long getParentRank() {
        return parentRank;
    }

    public long getChildRank() {
        return childRank;
    }

    public ADVBinaryTreeNode<?> getChildNode() {
        return childNode;
    }
}
