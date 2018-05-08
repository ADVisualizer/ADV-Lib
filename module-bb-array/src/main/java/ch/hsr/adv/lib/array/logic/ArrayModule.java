package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.Coordinate;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.Map;

/**
 * Encapsulates module meta data and array data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 *
 * @param <T> depends on the content of the array
 */
public interface ArrayModule<T> extends ADVModule {

    /**
     * @return the array data to be displayed
     */
    T[] getArray();

    @Override
    default String getModuleName() {
        return "array";
    }

    /**
     * @return optional fixed parameteres for the array elements. If set, the
     * placement will not be calculated by the ADVCore UI.
     */
    Map<Integer, Coordinate> getCoordinates();

    /**
     * Display objects as independent objects
     *
     * @return whether to show the independent objects or not
     */
    boolean showObjectRelations();

    /**
     * Returns the style map
     *
     * @return style map
     */
    Map<Integer, ADVStyle> getStyleMap();

}
