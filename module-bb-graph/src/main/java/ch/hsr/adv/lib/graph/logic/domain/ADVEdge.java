package ch.hsr.adv.lib.graph.logic.domain;

import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * An edge always has an end and a start vertex. It can be directed or
 * undirected.
 */
public interface ADVEdge<E> {
    /**
     * @return whether the edge is directed. If {@code false}, startVertex and
     * endVertex can be switched without changing the information fo the edge.
     */
    boolean isDirected();

    /**
     * @return the start vertex of the edge. Must not be {@code null}
     */
    ADVVertex getStarVertex();

    /**
     * @return the end vertex of the edge. Must not be {@code null}
     */
    ADVVertex getEndVertex();

    /**
     * @return the value of the edge. Could be a string or a weight etc.
     */
    E getValue();

    /**
     * @return the style of the edge
     */
    ADVStyle getStyle();
}
