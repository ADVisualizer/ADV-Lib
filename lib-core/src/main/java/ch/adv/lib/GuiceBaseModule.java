package ch.adv.lib;

import ch.adv.lib.access.Connector;
import ch.adv.lib.access.SocketConnector;
import com.google.inject.AbstractModule;

public class GuiceBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connector.class).to(SocketConnector.class);
    }
}
