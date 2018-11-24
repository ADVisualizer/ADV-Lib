package ch.hsr.adv.lib.tree.logic.util;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;

/**
 * functional interface to describe what should happen when a general tree is
 * traversed
 * @param <T> type of the content of a node
 */
@FunctionalInterface
public interface GeneralTraversalFunction<T> {
    /**
     * function of the interface
     * @param moduleGroup data sent to the ui
     * @param node the node
     * @param parentId the id of the parent node
     * @param childId the id of the node
     */
    void apply(ModuleGroup moduleGroup, ADVGeneralTreeNode<T> node,
               long parentId, long childId);
}
