package ch.adv.lib.array;

import ch.adv.lib.access.Stringifyer;
import ch.adv.lib.model.ADVModule;
import ch.adv.lib.model.Builder;
import com.google.inject.Guice;
import com.google.inject.Injector;

public interface ArrayModule<T> extends ADVModule {
    Injector injector = Guice.createInjector(new GuiceBaseModule());

    T[] getArray();

    @Override
    default Stringifyer getStringifyer() {
        return injector.getInstance(Stringifyer.class);
    }

    @Override
    default Builder getBuilder() {
        return injector.getInstance(Builder.class);
    }

    @Override
    default String getModuleName() {
        return "array";
    }


}
