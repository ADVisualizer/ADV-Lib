package ch.hsr.adv.lib.graph.logic.domain;

import java.util.Collection;

/**
 * A graph contains a set of edges and vertices and offeres methods to add
 * and remove them from its collections.
 *
 * @param <V> content of the vertices
 * @param <E> content of the edges
 * @author mtrentini
 */
public interface ADVGraph<V, E> {
    /**
     * Creates a vertex with the given value and adds it to the graph.
     *
     * @param value of the vertex to be created
     */
    ADVVertex<V> insertVertex(V value);

    /**
     * Creates an edge with the given value and adds it to the graph.
     *
     * @param value of the edge to be created
     * @param start vertex of the edge
     * @param end   vertex of the edge
     */
    ADVEdge<E> insertEdge(E value, ADVVertex<V> start, ADVVertex<V> end);

    /**
     * Remove the specified vertex and all its incident edges from the graph.
     *
     * @param vertex to be removed
     */
    void removeVertex(ADVVertex<V> vertex);

    /**
     * Remove the specified edge from the graph
     *
     * @param edge to be removed
     */
    void removeEdge(ADVEdge edge);

    /**
     * @return a collection of all the vertices of the graph
     */
    Collection<ADVVertex<V>> getVertices();

    /**
     * @return a collection of all the edges of the graph
     */
    Collection<ADVEdge<E>> getEdges();


}
