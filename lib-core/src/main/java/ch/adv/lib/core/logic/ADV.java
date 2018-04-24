package ch.adv.lib.core.logic;

import ch.adv.lib.core.access.Connector;
import ch.adv.lib.core.logic.domain.Session;
import ch.adv.lib.core.logic.util.*;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Main class of the Algorithm & Data Structure Visualizer
 * <p>
 * Please use the snapshot() method to transmit data to the ADV UI. The UI is
 * automatically started, if it is not already running.
 * <p>
 * Important:
 * Please call disconnect() after a successful transmission of your session!
 * <p>
 * Use command-line argument 'port' and 'host' to configure the adv ui socket:
 * <code>--port=9876 --host=localhost</code>
 *
 * @author mwieland
 */
@Singleton
public final class ADV {
    private static final int CONNECTION_TIMEOUT_MS = 1000;
    private static final int RETRY_LIMIT = 5;
    private static final String ADV_UI_MAIN =
            "ch.adv.ui.bootstrapper.Bootstrapper";
    private static final Logger logger = LoggerFactory.getLogger(ADV.class);
    private static ADV adv;
    private final ProcessExecutor processExecutor;
    private final ClasspathUtil classpathUtil;
    private final Connector socketConnector;
    private final CLIArgumentUtil cliUtil;

    @Inject
    public ADV(ProcessExecutor processExecutor, ClasspathUtil classpathUtil,
               Connector socketConnector, CLIArgumentUtil cliUtil) {
        this.processExecutor = processExecutor;
        this.classpathUtil = classpathUtil;
        this.socketConnector = socketConnector;
        this.cliUtil = cliUtil;
    }

    /**
     * Starts the Algorithm & Data Structure Visualizer.
     * <p>
     * Tries to automatically start the ADV UI if it can be found on the
     * classpath.
     * Opens a {@link java.net.Socket} connection to the ADV UI.
     *
     * @param args main method arguments
     * @throws ADVException if no connection can be established to the ADV UI
     */
    public static void launch(String[] args) throws ADVException {
        Injector injector = Guice.createInjector(new GuiceBaseModule());
        adv = injector.getInstance(ADV.class);
        adv.setup(args);
    }

    /**
     * Lets the session be built by the module builder.
     * Lets said session be stringifyed by the module stringifyer.
     * Hands the resulting json String to the connector;
     *
     * @param module              the module bundling the snapshot content
     * @param snapshotDescription an explanatory description for what is
     *                            happening in the snapshot
     */
    public static void snapshot(ADVModule module, String snapshotDescription) {
        Session session = module.getBuilder().build(module,
                snapshotDescription);
        String json = module.getStringifyer().stringify(session);
        adv.sendToSocket(json);
    }

    /**
     * Convenience method for optional snapshot description
     * <p>
     * Lets the session be built by the module builder.
     * Lets said session be stringifyed by the module stringifyer.
     * Hands the resulting json String to the connector;
     *
     * @param module the module bundling the snapshot content
     */
    public static void snapshot(ADVModule module) {
        snapshot(module, null);
    }

    /**
     * Closes the {@link java.net.Socket} to the ADV UI
     */
    public static void disconnect() {
        adv.disconnectFromSocket();
    }

    private void setup(String[] args) throws ADVException {
        parseCLIParams(args);
        checkDependencies();
        connectToUI(args);
    }

    /**
     * Checks command line arguments for configurable port number
     * <p>
     * Use command-line argument 'port' to configure the socket server:
     * <code>--port=9876</code>
     *
     * @param args command line arguments
     */
    private void parseCLIParams(String[] args) {
        Map<String, String> params = cliUtil.parseNamedParams(args);
        String port = params.get("port");
        if (port != null) {
            socketConnector.setPort(Integer.parseInt(port));
        }

        String host = params.get("host");
        if (host != null) {
            socketConnector.setHost(host);
        }
    }

    private void checkDependencies() {
        boolean onClassPath = classpathUtil.onClassPath(ADV_UI_MAIN);
        if (!onClassPath) {
            logger.warn("Unable to find ADV UI. Please update your project "
                    + "dependencies or start the UI manually. (java -jar "
                    + "/path/to/jar/adv-ui.jar)");
        }
    }

    private void connectToUI(String[] args) throws ADVException {
        boolean uiAvailable = socketConnector.connect();

        if (!uiAvailable) {

            Optional<ProcessHandle> handle = startUI(args);

            int connectionAttempts = 1;
            boolean connected = false;
            while (handle.get().isAlive() && !connected) {
                logger.info("{}. try to connect to ADV UI", connectionAttempts);
                connected = socketConnector.connect();
                connectionAttempts++;

                if (!connected) {

                    if (connectionAttempts > RETRY_LIMIT) {
                        break;
                    }
                    try {
                        Thread.sleep(CONNECTION_TIMEOUT_MS);
                    } catch (InterruptedException e) {
                        logger.error("Unable to sleep", e);
                    }
                }
            }

            if (!connected) {
                throw new ADVConnectionException("Unable to connect ADV UI");
            }
        }
    }

    private Optional<ProcessHandle> startUI(String[] args) {
        try {
            Process process = processExecutor.execute(ADV_UI_MAIN, args);
            return ProcessHandle.of(process.pid());
        } catch (IOException e) {
            logger.error("Unable to launch standalone process");
            return Optional.empty();
        }
    }

    private void sendToSocket(String json) {
        socketConnector.send(json);
    }

    private void disconnectFromSocket() {
        socketConnector.disconnect();
    }

}
