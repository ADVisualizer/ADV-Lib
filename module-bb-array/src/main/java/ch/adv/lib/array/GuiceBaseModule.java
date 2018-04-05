package ch.adv.lib.array;

import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.service.Stringifyer;
import com.google.inject.AbstractModule;

/**
 * Guice bindings for the array module.
 */
class GuiceBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Stringifyer.class).to(ArrayStringifyer.class);
        bind(Builder.class).to(ArrayBuilder.class);
    }
}
