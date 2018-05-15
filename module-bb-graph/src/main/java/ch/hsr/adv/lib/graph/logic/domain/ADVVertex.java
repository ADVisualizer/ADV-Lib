package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVElement;

/**
 * A Vertex holds a value.
 *
 * @param <T> the value of the vertex
 */
public interface ADVVertex<T> extends ADVElement<T> {

    /**
     * Number of leaving edges.
     *
     * @return count of edges that only leave this node plus all undirected
     * edges.
     */
    int getOutDegree();

    /**
     * Number of entering edges.
     *
     * @return count of edges that only enter this node plus all undirected
     * edges.
     */
    int getInDegree();

}
