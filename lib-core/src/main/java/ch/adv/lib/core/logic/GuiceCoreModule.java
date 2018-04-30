package ch.adv.lib.core.logic;

import ch.adv.lib.core.access.Connector;
import ch.adv.lib.core.access.GsonProvider;
import ch.adv.lib.core.access.JsonBuilderProvider;
import ch.adv.lib.core.access.SocketConnector;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * Guice DI configuration class.
 * <p>
 * If this class grows to a certain extend, it should be split up in
 * multiple modules.
 */
public class GuiceCoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connector.class).to(SocketConnector.class);

        bind(new TypeLiteral<JsonBuilderProvider<Gson>>() {
        })
                .to(new TypeLiteral<GsonProvider>() {
                });
    }
}
