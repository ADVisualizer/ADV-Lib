package ch.hsr.adv.lib.core.access;

import com.google.gson.GsonBuilder;
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

    private final JsonBuilderProvider<GsonBuilder> gsonProvider;

    private int portNr;
    private String host;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    @Inject
    public SocketConnector(JsonBuilderProvider<GsonBuilder> gsonProvider,
                           Socket socket) {
        this.portNr = DEFAULT_PORT;
        this.host = DEFAULT_HOST;
        this.gsonProvider = gsonProvider;
        this.socket = socket;
    }

    @Override
    public boolean connect() {
        try {
            socket.connect(new InetSocketAddress(host, portNr),
                    CONNECTION_TIMEOUT);
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            reader = new BufferedReader(new InputStreamReader(socket
                    .getInputStream(), StandardCharsets.UTF_8));
            logger.info("Successfully connected to UI on {}:{}", host, portNr);
            return true;
        } catch (IOException e) {
            logger.info("Unable to connect to UI on on {}:{}", host, portNr);
            return false;
        }
    }


    @Override
    public boolean setPort(int port) {
        if (port >= 1024 && port <= 65535) {
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
                String payload = gsonProvider.getMinifier().create()
                        .toJson(request);
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
            String payload = gsonProvider.getMinifier().create()
                    .toJson(request);
            writer.println(payload);
            logger.info("Waiting for acknowledgment...");

            String responseString = reader.readLine();
            ADVResponse response = gsonProvider.getMinifier().create()
                    .fromJson(responseString, ADVResponse.class);

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
