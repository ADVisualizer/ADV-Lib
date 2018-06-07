package ch.hsr.adv.lib.bootstrapper;

import ch.hsr.adv.commons.core.logic.util.ADVException;
import ch.hsr.adv.lib.core.logic.ADVCore;
import ch.hsr.adv.lib.core.logic.ADVModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry Point to the ADV Lib Application.
 * Initiates Guice to bootstrap the core with the modules.
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
     * Tries to automatically start the ADV UI if it can be found on the
     * classpath.
     * Opens a {@link java.net.Socket} connection to the ADV UI.
     *
     * @param args main method arguments
     * @throws ADVException if no connection to the
     *                      ADV UI can be established
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
     * Wires the snapshot to the lib core
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
     * Wires the snapshot to the lib core
     *
     * @param module the module bundling the snapshot content
     * @throws ADVException if no connection can be established to the
     *                      ADV UI
     */
    public static void snapshot(ADVModule module) throws ADVException {
        logger.info("Sending snapshot ...");
        if (advCore == null) {
            ADV.launch(null);
        }
        advCore.snapshot(module, null);
    }

    /**
     * Closes the {@link java.net.Socket} to the ADV UI
     *
     * @throws ADVException if no connection can be established to the
     *                      ADV UI
     */
    public static void disconnect() throws ADVException {
        logger.info("Starting disconnection...");
        if (advCore == null) {
            ADV.launch(null);
        }
        advCore.disconnectFromSocket();
    }
}
