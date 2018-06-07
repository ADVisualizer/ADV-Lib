package ch.hsr.adv.lib.bootstrapper;

import ch.hsr.adv.lib.core.access.Connector;
import ch.hsr.adv.lib.core.access.SocketConnector;
import com.google.inject.AbstractModule;

/**
 * Guice DI configuration class.
 * <p>
 * If this class grows to a confusing size, it should be split up in
 * multiple modules.
 */
public class GuiceCoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connector.class).to(SocketConnector.class);
    }
}
