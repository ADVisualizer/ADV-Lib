package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

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

    void setOutDegree(int degree);

    /**
     * Number of entering edges.
     *
     * @return count of edges that only enter this node plus all undirected
     * edges.
     */
    int getInDegree();

    void setInDegree(int degree);

    /**
     * @param value to be set
     */
    void setId(long value);

    /**
     * @param value to be set
     */
    void setContent(T value);

    /**
     * @param style to be set
     */
    void setStyle(ADVStyle style);

    /**
     * @param x coordinate to be set
     */
    void setFixedPosX(int x);

    /**
     * @param y coordinate to be set
     */
    void setFixedPosY(int y);
}
