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

    /**
     * Number of entering edges.
     *
     * @return count of edges that only enter this node plus all undirected
     * edges.
     */
    int getInDegree();

    /**
     * Sets the id of a vertex
     *
     * @param id identifier
     */
    void setId(long id);

    /**
     * Sets the style of a vertex
     *
     * @param style style
     */
    void setStyle(ADVStyle style);

    /**
     * Sets the fixed X position of a vertex
     *
     * @param fixedPosX position X
     */
    void setFixedPosX(int fixedPosX);

    /**
     * Sets the fixed Y position of a vertex
     *
     * @param fixedPosY position Y
     */
    void setFixedPosY(int fixedPosY);

    /**
     * Sets the label content of a vertex
     *
     * @param content content
     */
    void setContent(T content);

}
