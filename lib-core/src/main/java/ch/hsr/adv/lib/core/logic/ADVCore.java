package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.Session;
import ch.hsr.adv.commons.core.logic.util.ADVException;
import ch.hsr.adv.lib.core.access.Connector;
import ch.hsr.adv.lib.core.logic.exceptions.ADVConnectionException;
import ch.hsr.adv.lib.core.logic.util.CLIArgumentUtil;
import ch.hsr.adv.lib.core.logic.util.ClasspathUtil;
import ch.hsr.adv.lib.core.logic.util.ProcessExecutor;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Main class of the ADV Lib
 * <p>
 * Handles connecting and sending data to the ADV UI.
 *
 * @author mwieland
 */
@Singleton
public final class ADVCore {
    private static final Logger logger = LoggerFactory.getLogger(ADVCore.class);

    private static final String ADV_UI_MAIN =
            "ch.hsr.adv.ui.bootstrapper.Bootstrapper";
    private static final int CONNECTION_TIMEOUT_MS = 1000;
    private static final int RETRY_LIMIT = 5;

    private final ProcessExecutor processExecutor;
    private final ClasspathUtil classpathUtil;
    private final Connector connector;
    private final CLIArgumentUtil cliUtil;
    private final ServiceProvider serviceProvider;
    private final CoreBuilder coreBuilder;
    private final CoreStringifyer coreStringifyer;

    @Inject
    public ADVCore(ProcessExecutor processExecutor,
                   ClasspathUtil classpathUtil,
                   Connector connector,
                   CLIArgumentUtil cliUtil,
                   ServiceProvider serviceProvider,
                   CoreBuilder coreBuilder,
                   CoreStringifyer coreStringifyer) {

        this.processExecutor = processExecutor;
        this.classpathUtil = classpathUtil;
        this.connector = connector;
        this.cliUtil = cliUtil;
        this.serviceProvider = serviceProvider;
        this.coreBuilder = coreBuilder;
        this.coreStringifyer = coreStringifyer;
    }

    /**
     * Lets the session be built by the core builder and the module builder.
     * Lets said session be stringifyed by the core stringifyer.
     * Hands the resulting json String to the connector;
     *
     * @param module              the module bundling the snapshot content
     * @param snapshotDescription an explanatory description for what is
     *                            happening in the snapshot
     */
    public void snapshot(ADVModule module, String snapshotDescription) {

        Session session = coreBuilder.build(module, snapshotDescription);

        ModuleGroup group = serviceProvider.getBuilder(
                module.getModuleName()).build(module);
        session.getFirstSnapshot().getModuleGroups().add(group);

        if (module.getChildModules() != null) {
            module.getChildModules().forEach(childModule -> {
                ModuleGroup childGroup = serviceProvider.getBuilder(childModule
                        .getModuleName())
                        .build(childModule);
                session.getFirstSnapshot().getModuleGroups().add(childGroup);
            });
        }

        String json = coreStringifyer.stringify(session);
        connector.send(json);
    }


    /**
     * Starts the Algorithm &amp; Data Structure Visualizer Application.
     * <p>
     * Tries to automatically start the ADV UI if it can be found on the
     * classpath.
     * Opens a {@link java.net.Socket} connection to the ADV UI.
     *
     * @param args main method arguments
     * @throws ADVException if no connection can be established to the
     *                      ADV UI
     */
    public void setup(String[] args) throws ADVException {
        parseCLIParams(args);
        checkDependencies();
        connectToUI(args);
    }

    /**
     * Checks command line arguments for configurable port number and host
     * <p>
     * Use command-line argument 'port' and 'host' to configure the socket
     * server:
     * <code>--host=192.168.1.10 --port=9876</code>
     *
     * @param args command line arguments
     */
    private void parseCLIParams(String[] args) {
        if (args != null) {
            Map<String, String> params = cliUtil.parseNamedParams(args);
            String port = params.get("port");
            if (port != null) {
                connector.setPort(Integer.parseInt(port));
            }

            String host = params.get("host");
            if (host != null) {
                connector.setHost(host);
            }
        }
    }

    private void checkDependencies() {
        boolean onClassPath = classpathUtil.onClassPath(ADV_UI_MAIN);
        if (!onClassPath) {
            logger.warn("Unable to find ADV UI. Please update your project "
                    + "dependencies or start the UI manually. (java -jar "
                    + "/path/to/jar/adv-ui-<version>.jar)");
        }
    }

    private void connectToUI(String[] args) throws ADVException {
        boolean uiAvailable = connector.connect();

        if (!uiAvailable) {

            Optional<ProcessHandle> handle = startUI(args);

            int connectionAttempts = 1;
            boolean connected = false;
            while (handle.get().isAlive() && !connected) {
                logger.info("{}. try to connect to ADVCore UI",
                        connectionAttempts);
                connected = connector.connect();
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
                throw new ADVConnectionException("Unable to connect to ADV "
                        + "UI");
            }
        }
    }

    private Optional<ProcessHandle> startUI(String[] args) {
        try {
            Process process;
            if (args != null) {
                process = processExecutor.execute(ADV_UI_MAIN, args);
            } else {
                process = processExecutor.execute(ADV_UI_MAIN);
            }
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
        connector.disconnect();
    }

}
