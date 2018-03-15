package ch.adv.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

/**
 * Establishes communications via sockets with adv-ui and sends snapshots.
 *
 * @author mtrentini
 */
@Singleton
public class SocketConnector {

    private int portNr;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private static final String SERVER_NAME = "127.0.0.1";

    private static final int DEFAULT_PORT = 8765;
    private static final Logger logger = LoggerFactory.getLogger(SocketConnector.class);

    @Inject
    public SocketConnector() {
        this.portNr = DEFAULT_PORT;
    }

    /**
     * Establishes a duplex socket connection to adv-ui.
     *
     * @return whether the connection was established successfully
     */
    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER_NAME, portNr));
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            logger.info("Successfully connected to UI on port {}", portNr);
            return true;
        } catch (IOException e) {
            logger.info("Unable to connect to UI on port {}. Try again..", portNr);
            return false;
        }
    }

    public void setPort(int portNr) {
        if (portNr >= 1024 && portNr <= 65535) {
            this.portNr = portNr;
        } else {
            this.portNr = DEFAULT_PORT;
        }
    }

    //TODO: move to stringifyer
    private long sessionNr;

    private void createSessionNr() {
        Calendar cal = Calendar.getInstance();
        sessionNr = cal.getTimeInMillis();
    }

    /**
     * Disconnects the socket to adv-ui.
     *
     * @return whether the line has been severed successfully
     */
    public boolean disconnect() {
        try {
            if (writer != null) {
                writer.println("END");
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
     * Sends a stringyfied snapshot to adv-ui
     *
     * @param snapshot a stringyfied snapshot
     * @return whether the data was sent successfully
     */
    public boolean send(String snapshot) {
        logger.info("Sending snapshot...");
        try {
            if (writer != null) {
                writer.println(snapshot);
                logger.info("Waiting for acknowledgment...");
                String response = reader.readLine();
                logger.debug("Response:" + response);
                if (response != null && response.equals("OK")) {
                    logger.info("Data has been received");
                    return true;
                }
                //TODO: handle different responses, e.g. Exceptions
                logger.error("Data could not be transmitted");
                return false;
            }
            logger.error("No connection to ADV-UI established");
            return false;
        } catch (IOException e) {
            logger.error("Unable to close all connections to ADV-UI", e);
            return false;
        }
    }
}
