package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.ADVRelation;

import java.util.Collection;
import java.util.List;

/**
 * A graph contains a set of edges and vertices and offeres methods to add
 * and remove them from its collections.
 *
 * @author mwieland
 */
public interface ADVGraph<V extends ADVElement, E extends ADVRelation> {

    void addVertex(V vertex);

    void addEdge(E edge);

    /**
     * Remove the specified vertex and all its incident edges from the graph.
     *
     * @param vertex to be removed
     */
    void removeVertex(V vertex);

    /**
     * Remove the specified edge from the graph
     *
     * @param edge to be removed
     */
    void removeEdge(E edge);

    /**
     * @return a collection of all the vertices of the graph
     */
    Collection<V> getVertices();

    /**
     * @return a collection of all the edges of the graph
     */
    Collection<E> getEdges();

    /**
     * Get a vertex by its identifier
     *
     * @param id identifier
     * @return vertex
     */
    V getVertex(long id);

    /**
     * Empties the graph completely by removing all vertices and edges.
     */
    void clear();

    List<ADVVertex> getNeighbors(ADVVertex source);

}
