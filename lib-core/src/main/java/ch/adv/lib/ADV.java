package ch.adv.lib;

import ch.adv.lib.util.CLIArgumentUtil;
import ch.adv.lib.util.ClasspathUtil;
import ch.adv.lib.util.ProcessExecutor;
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
 * Main class of ADV Lib.
 * If necessary, starts the ADV UI.
 * <p>
 * Use command-line argument 'port' to configure the adv ui port: <code>--port=9876</code>
 *
 * @author mwieland
 */

@Singleton
public class ADV {

    private final ProcessExecutor processExecutor;
    private final ClasspathUtil classpathUtil;
    private final SocketConnector socketConnector;
    private final CLIArgumentUtil cliUtil;

    private final static int CONNECTION_TIMEOUT_MS = 1000;
    private static final int RETRY_LIMIT = 5;

    private static final String ADV_UI_MAIN = "ch.adv.ui.ADVApplication";

    private static final Logger logger = LoggerFactory.getLogger(ADV.class);

    @Inject
    public ADV(ProcessExecutor processExecutor, ClasspathUtil classpathUtil, SocketConnector socketConnector, CLIArgumentUtil cliUtil) {
        this.processExecutor = processExecutor;
        this.classpathUtil = classpathUtil;
        this.socketConnector = socketConnector;
        this.cliUtil = cliUtil;
    }

    /**
     * Checks whether UI is in classpath, starts the ui process and opens a connection.
     */
    public static ADV launch(String[] args) throws ADVException {
        Injector injector = Guice.createInjector(new GuiceBaseModule());
        ADV adv = injector.getInstance(ADV.class);
        adv.setup(args);
        return adv;
    }

    private void setup(String[] args) throws ADVException {
        parseCLIParams(args);
        checkDependencies();
        connectToUI();
    }

    /**
     * Checks command line arguments for configurable port number
     *
     * @param args command line arguments
     */
    private void parseCLIParams(String[] args) {
        Map<String, String> params = cliUtil.parseNamedParams(args);
        String port = params.get("port");
        if (port != null) {
            socketConnector.setPort(Integer.parseInt(port));
        }
    }

    private void checkDependencies() {
        boolean onClassPath = classpathUtil.onClassPath(ADV_UI_MAIN);
        if (!onClassPath) {
            logger.warn("Unable to find ADV UI. Please update your project dependencies or start the UI for yourself. (java -jar adv-ui.jar)");
        }
    }

    private void connectToUI() throws ADVException {
        boolean uiAvailable = socketConnector.connect();

        if (!uiAvailable) {

            Optional<ProcessHandle> handle = startUI();

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

    private Optional<ProcessHandle> startUI() {
        try {
            Process process = processExecutor.execute(ADV_UI_MAIN);
            return ProcessHandle.of(process.pid());
        } catch (IOException e) {
            logger.error("Unable to launch standalone process");
            return Optional.empty();
        }
    }

    public void snapshot(ADVModule module) {
        // transmit module to ADV UI

        socketConnector.send(module.toString());
        module.getStyleMap().values().forEach((advStyle -> socketConnector.send(advStyle.toString())));
    }

    public void disconnect() {
        socketConnector.disconnect();
    }

}
