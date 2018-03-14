package ch.adv.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

/**
 * Establishes communications via sockets with adv-ui and sends snapshots.
 *
 * @author mtrentini
 */
public class SocketConnection {
    private static Socket socket;
    private static PrintWriter writer;
    private static BufferedReader reader;
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int DEFAULT_PORT = 8765;
    private static final Logger logger = LoggerFactory.getLogger(SocketConnection.class);

    /**
     * Establishes a duplex socket connection to adv-ui.
     *
     * @return whether the connection was established successfully
     */
    public static boolean connect() {
        try {
            socket = new Socket(SERVER_NAME, DEFAULT_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return true;
        } catch (IOException e) {
            logger.error("Unable to establish connection to ADV-UI", e);
            return false;
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
     * @return whether the line has been severed successfully
     */
    public static boolean disconnect() {
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
    public static boolean send(String snapshot) {
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
