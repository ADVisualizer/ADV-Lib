package ch.adv.lib.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Establishes communications via sockets with ADV UI and sends snapshots.
 *
 * @author mtrentini
 */
@Singleton
public class SocketConnector implements Connector {

    private static final String SERVER_NAME = "127.0.0.1";
    private static final int DEFAULT_PORT = 8765;
    private static final Logger logger = LoggerFactory.getLogger(
            SocketConnector.class);
    private final GsonProvider gsonProvider;
    private int portNr;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    /**
     * Default constructor
     */
    @Inject
    public SocketConnector(GsonProvider gsonProvider) {
        this.portNr = DEFAULT_PORT;
        this.gsonProvider = gsonProvider;
    }

    /**
     * Establishes a duplex socket connection to ADV UI.
     *
     * @return whether the connection was established successfully
     */
    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER_NAME, portNr));
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket
                    .getInputStream(), StandardCharsets.UTF_8));
            logger.info("Successfully connected to UI on port {}", portNr);
            return true;
        } catch (IOException e) {
            logger.info("Unable to connect to UI on port {}.    ", portNr);
            return false;
        }
    }

    /**
     * Sets an alternative port-number to be used
     *
     * @param port the port number of the server
     */
    public void setPort(int port) {
        if (port >= 1024 && port <= 65535) {
            this.portNr = port;
        } else {
            this.portNr = DEFAULT_PORT;
        }
    }

    /**
     * Disconnects the socket to ADV UI.
     *
     * @return whether the line has been severed successfully
     */
    public boolean disconnect() {
        try {
            if (writer != null) {
                ADVRequest request = new ADVRequest(ProtocolCommand.END);
                writer.println(request.toJson());
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
            logger.info("Connections to ADV-UI have been closed");
            return true;
        } catch (IOException e) {
            logger.error("Unable to close all connections to ADV-UI", e);
            return false;
        }
    }

    /**
     * Sends a stringyfied snapshot to ADV UI
     *
     * @param snapshot a stringyfied snapshot
     * @return whether the data was sent successfully
     */
    public boolean send(String snapshot) {
        logger.info("Sending snapshot...");
        try {
            ADVRequest request = new ADVRequest(ProtocolCommand.TRANSMIT,
                    snapshot);
            writer.println(request.toJson());
            logger.info("Waiting for acknowledgment...");

            String responseString = reader.readLine();
            ADVResponse response = gsonProvider.getMinifier().fromJson(
                    responseString, ADVResponse.class);

            if (response.getCommand().equals(ProtocolCommand.ACKNOWLEDGE)) {
                logger.info("Data has been received");
                return true;
            } else {
                logger.error("Exception in UI occurred. {}", response
                        .getExceptionMessage());
                return false;
            }
        } catch (IOException e) {
            logger.error("Unable to send snapshot to ADV-UI", e);
            return false;
        }
    }
}
