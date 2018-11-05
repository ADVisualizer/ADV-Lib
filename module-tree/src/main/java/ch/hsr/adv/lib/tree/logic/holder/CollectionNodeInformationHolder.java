package ch.hsr.adv.lib.tree.logic.holder;

import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * CollectionNodeInformationHolder extends NodeInformationHolder with
 * information for children of a node
 */
public class CollectionNodeInformationHolder
        extends NodeInformationHolder<ADVGeneralTreeNode<?>> {

    private List<CollectionNodeInformationHolder> children;

    public CollectionNodeInformationHolder(long parentRank, long childRank,
                                           ADVGeneralTreeNode<?> childNode) {
        super(parentRank, childRank, childNode);
        children = new ArrayList<>();
    }

    /**
     * adds a child to the holder
     *
     * @param child new child
     */
    public void addChild(CollectionNodeInformationHolder child) {
        children.add(child);
    }

    public List<CollectionNodeInformationHolder> getChildren() {
        return children;
    }
}
