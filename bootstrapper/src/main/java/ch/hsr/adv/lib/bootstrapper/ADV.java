package ch.hsr.adv.lib.bootstrapper;

import ch.hsr.adv.commons.core.logic.util.ADVException;
import ch.hsr.adv.lib.core.logic.ADVCore;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.GuiceCoreModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry Point to the ADVCore Application.
 * Registers all available modules with Guice
 *
 * @author mtrentini
 */
@Singleton
public class ADV {
    private static final Logger logger =
            LoggerFactory.getLogger(ADV.class);

    private static ADVCore advCore;

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
    public static void launch(String[] args) throws ADVException {
        Injector injector = createInjector();
        advCore = injector.getInstance(ADVCore.class);
        logger.info("Launching ADV Application ... ");
        advCore.setup(args);
    }

    /**
     * @return an injector with the GuiceModule of each ADV Module.
     */
    private static Injector createInjector() {
        return Guice.createInjector(
                new GuiceCoreModule(),
                new GuiceBootstrapperModule()
        );
    }

    /**
     * Lets the session be built by the module builder.
     * Lets said session be stringifyed by the module stringifyer.
     * Hands the resulting json String to the connector;
     *
     * @param module              the module bundling the snapshot content
     * @param snapshotDescription an explanatory description for what is
     *                            happening in the snapshot
     * @throws ADVException if no connection can be established to the
     *                      ADVCore UI
     */
    public static void snapshot(ADVModule module, String snapshotDescription)
            throws ADVException {
        logger.info("Initialize sending snapshot ...");
        if (advCore == null) {
            ADV.launch(null);
        }
        advCore.snapshot(module, snapshotDescription);
    }

    /**
     * Convenience method for optional snapshot description
     * <p>
     * Lets the session be built by the module builder.
     * Lets said session be stringifyed by the module stringifyer.
     * Hands the resulting json String to the connector;
     *
     * @param module the module bundling the snapshot content
     * @throws ADVException if no connection can be established to the
     *                      ADVCore UI
     */
    public static void snapshot(ADVModule module) throws ADVException {
        logger.info("Sending snapshot ...");
        if (advCore == null) {
            ADV.launch(null);
        }
        advCore.snapshot(module, null);
    }

    /**
     * Closes the {@link java.net.Socket} to the ADVCore UI
     *
     * @throws ADVException if no connection can be established to the
     * ADVCore UI
     */
    public static void disconnect() throws ADVException {
        logger.info("Starting disconnection...");
        if (advCore == null) {
            ADV.launch(null);
        }
        advCore.disconnectFromSocket();
    }
}
