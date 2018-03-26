package ch.adv.lib;

import ch.adv.lib.service.Connector;
import ch.adv.lib.service.SocketConnector;
import com.google.inject.AbstractModule;

/**
 * Guice DI configuration class.
 * <p>
 * If this class grows to a certain extend, it should be split up in
 * multiple modules.
 */
public class GuiceBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connector.class).to(SocketConnector.class);
    }
}
