package ch.adv.lib;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ADV {

    private static final String ADV_UI_MAIN = "ADVApplication";
    private static final String ADV_UI_JAR = "adv-ui-0.1.jar";

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
        boolean onClassPath = util.onClassPath(ADV_UI_JAR);
        if (!onClassPath) {
            throw new RuntimeException();
        }
    }

    private final void startUI() {
        try {
            ProcessExecutor processExecutor = new ProcessExecutor();
            processExecutor.executeJarByName(ADV_UI_JAR);
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
