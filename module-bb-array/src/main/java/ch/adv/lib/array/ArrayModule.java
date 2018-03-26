package ch.adv.lib.array;

import ch.adv.lib.ADVModule;
import ch.adv.lib.array.model.Coordinates;
import ch.adv.lib.logic.Builder;
import ch.adv.lib.logic.Stringifyer;

import java.util.Map;

public interface ArrayModule<T> extends ADVModule {

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

    Map<Integer, Coordinates> getCoordinates();

}
