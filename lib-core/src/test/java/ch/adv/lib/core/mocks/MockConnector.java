package ch.adv.lib.core.mocks;

import ch.adv.lib.core.service.Connector;

public class MockConnector implements Connector {
    @Override
    public boolean send(String snapshot) {
        return true;
    }

    @Override
    public boolean disconnect() {
        return true;
    }

    @Override
    public void setPort(int portNr) {

    }

    @Override
    public void setHost(String host) {

    }

    @Override
    public boolean connect() {
        return true;
    }
}
