package ch.adv.lib.array.logic;

import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.logic.Stringifyer;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

/**
 * Guice bindings for the array module.
 */
public class GuiceArrayModule extends AbstractModule {
    private static final String KEY = "array";
    @Override
    protected void configure() {
        MapBinder<String, Stringifyer> stringifyerMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Stringifyer
                .class);
        stringifyerMapBinder.addBinding(KEY).to(ArrayStringifyer.class);

        MapBinder<String, Builder> builderMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Builder.class);
        builderMapBinder.addBinding(KEY).to(ArrayBuilder.class);
    }
}
