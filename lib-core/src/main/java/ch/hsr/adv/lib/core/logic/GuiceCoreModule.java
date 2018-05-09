package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.access.Connector;
import ch.hsr.adv.lib.core.access.GsonProvider;
import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.access.SocketConnector;
import com.google.gson.GsonBuilder;
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

        bind(new TypeLiteral<JsonBuilderProvider<GsonBuilder>>() {
        }).to(new TypeLiteral<GsonProvider>() {
        });
    }
}
