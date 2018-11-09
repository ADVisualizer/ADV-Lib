package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.tree.logic.collectiontree.CollectionTreeModule;

import java.util.Set;

public class CollectionTreeTestModule<T> extends CollectionTreeModule<T> {

    public CollectionTreeTestModule(String sessionName) {
        super(sessionName);
    }

    @Override
    public Set<ADVGeneralTreeNode<T>> getNodes() {
        return super.getNodes();
    }
}
