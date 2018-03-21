package ch.adv.lib.array;

import ch.adv.lib.logic.Builder;
import ch.adv.lib.service.Stringifyer;
import com.google.inject.AbstractModule;

public class GuiceBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Stringifyer.class).to(ArrayStringifyer.class);
        bind(Builder.class).to(ArrayBuilder.class);
    }
}
