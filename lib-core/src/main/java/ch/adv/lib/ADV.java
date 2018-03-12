package ch.adv.lib;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ADV {

    private static final String ADV_UI_JAR = "adv-ui-0.1.jar";

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Checks whether UI is in classpath, start the ui thead and open a connection.
     */
    public static void launch()  {
        try {
            ProcessExecutor processExecutor = new ProcessExecutor();
            processExecutor.executeJarByName(ADV_UI_JAR);
        } catch (IOException e) {
            logger.info("Unable to launch standalone process");
        }
    }
}
