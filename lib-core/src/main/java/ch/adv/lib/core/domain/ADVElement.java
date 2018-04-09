package ch.adv.lib.core.domain;

import ch.adv.lib.core.domain.styles.presets.ADVDefaultStyle;
import ch.adv.lib.core.domain.styles.ADVStyle;

/**
 * Represents an element of a snapshot. An
 * element could for example be a node in a graph.
 *
 * @param <T> Type of content. Could either be a simple Java class like
 *            String or a more complexe class designed by the module developer.
 */
public interface ADVElement<T> {
    /**
     * Gets a unique id of the element. The id only needs to be unique within
     * one snapshot. Preferably use the same id for the same element in
     * different snapshots.
     *
     * @return a snapshot-wide unique id
     */
    long getElementId();

    /**
     * Returns the style of the element. Use
     * {@link ADVDefaultStyle} for a default
     * implementation.
     *
     * @return the style of the element
     */
    ADVStyle getStyle();

    /**
     * Offers the user the possibility to set the position of an element.
     * The position will be ignored by the ui if it equals 0;
     *
     * @return the x position of the element
     */
    int getFixedPosX();

    /**
     * Offers the user the possibility to set the position of an element.
     * The position will be ignored by the ui if it equals 0;
     *
     * @return the y position of the element
     */
    int getFixedPosY();

    /**
     * The content could either be a simple Java class like String or a more
     * complexe class designed by the module developer.
     *
     * @return the content of the element
     */
    T getContent();
}
