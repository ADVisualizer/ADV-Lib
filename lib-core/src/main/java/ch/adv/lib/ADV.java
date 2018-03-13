package ch.adv.lib;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ADV {

    private static final String ADV_UI_MAIN = "ch.adv.ui.ADVApplication";

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Checks whether UI is in classpath, start the ui thead and open a connection.
     */
    public static void launch()  {
        ADV adv = new ADV();

        adv.checkDependencies();
        adv.startUI();
        adv.connect();
    }

    private final void checkDependencies() {
        ClasspathUtil util = new ClasspathUtil();
        boolean onClassPath = util.onClassPath(ADV_UI_MAIN);
        if (!onClassPath) {
            logger.warn("Unable to find ADV UI. Please update your project dependencies or start the UI for yourself. (java -jar adv-ui.jar)");
        }
    }

    private final void startUI() {
        try {
            ProcessExecutor processExecutor = new ProcessExecutor();
            processExecutor.execute(ADV_UI_MAIN);
        } catch (IOException e) {
            logger.info("Unable to launch standalone process");
        }
    }

    private final void connect() {
        // TODO open socket
    }

    public static void snapshot(ADVModule module) {
        // transmit module to ADV UI
        module.getStyleMap().values().forEach(System.out::println);
    }
}
