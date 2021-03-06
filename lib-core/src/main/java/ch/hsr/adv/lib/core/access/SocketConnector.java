package ch.hsr.adv.lib.core.access;

import ch.hsr.adv.commons.core.access.ADVRequest;
import ch.hsr.adv.commons.core.access.ADVResponse;
import ch.hsr.adv.commons.core.access.GsonProvider;
import ch.hsr.adv.commons.core.access.ProtocolCommand;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Establishes communications via sockets with ADVCore UI and sends snapshots.
 *
 * @author mtrentini
 */
@Singleton
public class SocketConnector implements Connector {

    private static final Logger logger = LoggerFactory
            .getLogger(SocketConnector.class);


    private static final int CONNECTION_TIMEOUT = 1_000;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 8765;

    private static final int REGISTERED_PORT_MIN = 1024;
    private static final int REGISTERED_PORT_MAX = 65535;

    private final Gson gson;

    private int portNr;
    private String host;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    @Inject
    public SocketConnector(GsonProvider gsonProvider) {
        this.portNr = DEFAULT_PORT;
        this.host = DEFAULT_HOST;
        this.gson = gsonProvider.getMinifier().create();
    }

    @Override
    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, portNr),
                    CONNECTION_TIMEOUT);
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket
                    .getInputStream(), StandardCharsets.UTF_8));
            logger.info("Successfully connected to UI on {}:{}", host, portNr);
            return true;
        } catch (IOException e) {
            logger.error("Unable to connect to UI on on {}:{}",
                    host, portNr, e);
            return false;
        }
    }


    @Override
    public boolean setPort(int port) {
        if (port >= REGISTERED_PORT_MIN && port <= REGISTERED_PORT_MAX) {
            this.portNr = port;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setHost(String hostName) {
        if (hostName != null) {
            this.host = hostName;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            if (writer != null) {
                ADVRequest request = new ADVRequest(ProtocolCommand.END);
                String payload = gson.toJson(request);
                writer.println(payload);
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
            logger.info("Connections to ADVCore-UI have been closed");
            return true;
        } catch (IOException e) {
            logger.error("Unable to close all connections to ADVCore-UI", e);
            return false;
        }
    }

    @Override
    public boolean send(String snapshot) {
        logger.info("Sending snapshot...");
        try {
            ADVRequest request = new ADVRequest(ProtocolCommand.TRANSMIT,
                    snapshot);
            String payload = gson.toJson(request);
            writer.println(payload);
            logger.info("Waiting for acknowledgment...");

            String responseString = reader.readLine();
            ADVResponse response = gson.fromJson(responseString,
                    ADVResponse.class);

            if (response.getCommand().equals(ProtocolCommand.ACKNOWLEDGE)) {
                logger.info("Data has been received");
                return true;
            } else {
                logger.error("Exception in UI occurred. {}", response
                        .getExceptionMessage());
                return false;
            }
        } catch (IOException e) {
            logger.error("Unable to send snapshot to ADVCore-UI", e);
            return false;
        }
    }
}
