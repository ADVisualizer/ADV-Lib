package ch.adv.lib.mocks;

import ch.adv.lib.access.Connector;

public final class MockConnector implements Connector{
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
    public boolean connect() {
        return true;
    }
}
