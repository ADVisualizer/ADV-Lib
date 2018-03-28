package ch.adv.lib.array;

import ch.adv.lib.logic.ADVModule;
import ch.adv.lib.array.model.Coordinates;
import ch.adv.lib.logic.Builder;
import ch.adv.lib.logic.Stringifyer;

import java.util.Map;

/**
 * Encapsulates business logic classes needed to to transform array data into
 * json to be sent to the ADV UI. It is not recommended to override the
 * default methods!
 *
 * @param <T> depends on the content of the array
 */
public interface ArrayModule<T> extends ADVModule {
    /**
     * @return the array data to be displayed
     */
    T[] getArray();

    @Override
    default Stringifyer getStringifyer() {
        return new ArrayStringifyer();
    }

    @Override
    default Builder getBuilder() {
        return new ArrayBuilder();
    }

    @Override
    default String getModuleName() {
        return "array";
    }

    /**
     * @return optional fixed parameteres for the array elements. If set, the
     * placement will not be calculated by the ADV UI.
     */
    Map<Integer, Coordinates> getCoordinates();

}
