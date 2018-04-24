package ch.adv.lib.core.access;

/**
 * Offeres methods to connect the ADV Lib to the ADV UI over a communication
 * channel like a socket.
 */
public interface Connector {
    /**
     * Sends a snapshot over an open communication channel.
     *
     * @param snapshot to be sent
     * @return true, if the data was sent successfully.
     */
    boolean send(String snapshot);

    /**
     * Disconnects from the communication channel. Should be called before
     * the process exits.
     *
     * @return true, if the disconnection was successful.
     */
    boolean disconnect();

    /**
     * Sets the portNr, where the ADV UI can be reached.
     *
     * @param portNr to be set
     */
    void setPort(int portNr);

    /**
     * Sets the host, where the ADV UI can be reached.
     *
     * @param host to be set
     */
    void setHost(String host);

    /**
     * Opens the communication channel to the ADV UI. Needs to be called
     * before {@link Connector#send(String)}  send()}.
     *
     * @return true, if the connection coulb be established successfully.
     */
    boolean connect();

}
