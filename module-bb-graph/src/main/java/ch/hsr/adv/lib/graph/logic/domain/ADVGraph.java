package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ADVRelation;

import java.util.Collection;
import java.util.List;

/**
 * A graph contains a set of edges and vertices and offeres methods to add
 * and remove them from its collections.
 *
 * @param <V> vertex type
 * @param <E> edge type
 * @author mwieland
 */
public interface ADVGraph<V extends ADVElement, E extends ADVRelation> {

    /**
     * Adds a new vertex to the graph
     *
     * @param vertex vertex
     */
    void addVertex(V vertex);

    /**
     * Adds new vertices to the graph
     *
     * @param vertices vertex
     */
    void addVertices(V... vertices);

    /**
     * Adds a new edge to the graph
     *
     * @param edge edge
     */
    void addEdge(E edge);

    /**
     * Adds new edges to the graph
     *
     * @param edges edges
     */
    void addEdges(E... edges);

    /**
     * Removes the specified vertex and all its incident edges from the graph.
     *
     * @param vertex to be removed
     */
    void removeVertex(V vertex);

    /**
     * Removes the specified edge from the graph
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

    /**
     * Returns all neighbor vertices a the given vertex
     *
     * @param source vertex
     * @return neighbors
     */
    List<ADVVertex> getNeighbors(ADVVertex source);

}
