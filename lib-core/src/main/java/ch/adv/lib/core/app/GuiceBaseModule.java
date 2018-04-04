package ch.adv.lib.core.app;

import ch.adv.lib.core.service.Connector;
import ch.adv.lib.core.service.SocketConnector;
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
