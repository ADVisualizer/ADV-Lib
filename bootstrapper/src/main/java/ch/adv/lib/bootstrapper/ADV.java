package ch.adv.lib.bootstrapper;

import ch.adv.lib.array.logic.GuiceArrayModule;
import ch.adv.lib.core.logic.ADVCore;
import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.GuiceCoreModule;
import ch.adv.lib.core.logic.util.ADVException;
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
     * Starts the Algorithm & Data Structure Visualizer.
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
                new GuiceArrayModule()
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
     */
    public static void snapshot(ADVModule module, String snapshotDescription) {
        logger.info("Initialize sending snapshot ...");
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
     */
    public static void snapshot(ADVModule module) {
        logger.info("Initialize sending snapshot ...");
        advCore.snapshot(module, null);
    }

    /**
     * Closes the {@link java.net.Socket} to the ADVCore UI
     */
    public static void disconnect() {
        logger.info("Starting disconnection...");
        advCore.disconnectFromSocket();
    }
}
