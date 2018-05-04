package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.access.Connector;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.util.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Main class of the Algorithm &amp; Data Structure Visualizer
 * <p>
 * Please use the snapshot() method to transmit data to the ADVCore UI. The
 * UI is
 * automatically started, if it is not already running.
 * <p>
 * Important:
 * Please call disconnect() after a successful transmission of your session!
 * <p>
 * Use command-line argument 'port' and 'host' to configure the advCore ui
 * socket:
 * <code>--port=9876 --host=localhost</code>
 *
 * @author mwieland
 */
@Singleton
public final class ADVCore {
    private static final int CONNECTION_TIMEOUT_MS = 1000;
    private static final int RETRY_LIMIT = 5;
    private static final String ADV_UI_MAIN =
            "ch.hsr.adv.ui.bootstrapper.Bootstrapper";
    private static final Logger logger = LoggerFactory.getLogger(ADVCore.class);
    private final ProcessExecutor processExecutor;
    private final ClasspathUtil classpathUtil;
    private final Connector socketConnector;
    private final CLIArgumentUtil cliUtil;
    private final ServiceProvider serviceProvider;

    @Inject
    public ADVCore(ProcessExecutor processExecutor,
                   ClasspathUtil classpathUtil,
                   Connector socketConnector,
                   CLIArgumentUtil cliUtil,
                   ServiceProvider serviceProvider) {

        this.processExecutor = processExecutor;
        this.classpathUtil = classpathUtil;
        this.socketConnector = socketConnector;
        this.cliUtil = cliUtil;
        this.serviceProvider = serviceProvider;
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
    public void snapshot(ADVModule module, String snapshotDescription) {
        String key = module.getModuleName();
        Session session = serviceProvider.getBuilder(key).build(module,
                snapshotDescription);
        String json = serviceProvider.getStringifyer(key).stringify(session);
        socketConnector.send(json);
    }


    /**
     * Starts the Algorithm &amp; Data Structure Visualizer.
     * <p>
     * Tries to automatically start the ADVCore UI if it can be found on the
     * classpath.
     * Opens a {@link java.net.Socket} connection to the ADVCore UI.
     *
     * @param args main method arguments
     * @throws ADVException if no connection can be established to the
     *                      ADVCore UI
     */
    public void setup(String[] args) throws ADVException {
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
                logger.info("{}. try to connect to ADVCore UI",
                        connectionAttempts);
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
                throw new ADVConnectionException("Unable to connect ADVCore "
                        + "UI");
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

    /**
     * Closes the {@link java.net.Socket} to the ADVCore UI
     */
    public void disconnectFromSocket() {
        socketConnector.disconnect();
    }

}
