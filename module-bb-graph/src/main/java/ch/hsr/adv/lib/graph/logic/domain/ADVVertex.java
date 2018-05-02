package ch.hsr.adv.lib.graph.logic.domain;

import ch.adv.lib.core.logic.domain.styles.ADVStyle;

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
     * @return the style of the vertex
     */
    ADVStyle getStyle();

    /**
     * @return the optionally set fixed x position of this vertex. The value
     * 0 will be interpreted as "not set"
     */
    public int getFixedPosX();

    /**
     * @return the optionally set fixed y position of this vertex. The value
     * 0 will be interpreted as "not set"
     */
    public int getFixedPosY();
}
