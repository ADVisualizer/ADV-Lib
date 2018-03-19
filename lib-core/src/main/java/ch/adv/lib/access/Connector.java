package ch.adv.lib.access;

public interface Connector {
    boolean send(String snapshot);
    boolean disconnect();
    void setPort(int portNr);
    boolean connect();
}
