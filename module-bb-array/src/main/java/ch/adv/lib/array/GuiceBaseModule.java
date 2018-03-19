package ch.adv.lib.array;

import ch.adv.lib.access.Stringifyer;
import ch.adv.lib.model.Builder;
import com.google.inject.AbstractModule;

public class GuiceBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Stringifyer.class).to(ArrayStringifyer.class);
        bind(Builder.class).to(ArrayBuilder.class);
    }
}
