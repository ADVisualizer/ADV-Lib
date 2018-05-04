package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * A Vertex holds a value.
 *
 * @param <V> the value of the vertex
 */
public interface ADVVertex<V> {
    /**
     * @return the content of the vertex
     */
    V getValue();

    /**
     * @param value to be set
     */
    void setValue(V value);

    /**
     * @return the style of the vertex
     */
    ADVStyle getStyle();

    /**
     * @param style to be set
     */
    void setStyle(ADVStyle style);

    /**
     * @return the optionally set fixed x position of this vertex. The value
     * 0 will be interpreted as "not set"
     */
    int getFixedPosX();

    /**
     * @param x coordinate to be set
     */
    void setFixedPosX(int x);

    /**
     * @return the optionally set fixed y position of this vertex. The value
     * 0 will be interpreted as "not set"
     */
    int getFixedPosY();

    /**
     * @param y coordinate to be set
     */
    void setFixedPosY(int y);
}
