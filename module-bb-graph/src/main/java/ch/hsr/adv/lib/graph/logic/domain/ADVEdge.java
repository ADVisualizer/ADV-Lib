package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVRelation;

/**
 * An edge always has an end and a start vertex. It can be directed or
 * undirected.
 *
 * @param <T> type of the edge value
 */
public interface ADVEdge<T> extends ADVRelation<T> {

    /**
     * @param directed whether or not the edge is directed
     */
    void setDirected(boolean directed);

    /**
     * @return true if the source and target element is identical
     */
    boolean isSelfReference();
}
