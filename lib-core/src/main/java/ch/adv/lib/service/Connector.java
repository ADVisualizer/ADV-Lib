package ch.adv.lib.service;

public interface Connector {
    boolean send(String snapshot);

    boolean disconnect();

    void setPort(int portNr);

    boolean connect();
}
