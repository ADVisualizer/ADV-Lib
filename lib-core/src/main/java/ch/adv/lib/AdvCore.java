package ch.adv.lib;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class AdvCore {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Checks whether UI is in classpath, start the ui thead and open a connection.
     */
    public void connect(){
        if (isUIInClasspath()) {
            Process uiProcess = startAdvUI();
            if (uiProcess != null && uiProcess.isAlive()) {
                logger.info("has not yet terminated");
            } else {
                logger.info("process has terminated");
            }

            //TODO open stream
        }
    }

    private boolean isUIInClasspath() {
        try {
            getClass().getClassLoader().loadClass("ch.adv.ui.AdvApplication");
            return true;
        } catch (ClassNotFoundException e) {
            logger.error("ADV UI is missing on the classpath. Please update Gradle dependecies.");
            return false;
        }
    }

    private Process startAdvUI () {
        try {
            String[] startOptions = new String[] {"java", "-jar", "adv-root-1.0.jar"};
            Process ps= new ProcessBuilder().command(startOptions).start();

            ProcessHandle handle = ProcessHandle.current();
            logger.info("pid {} children {}", handle.pid(), handle.children().count());

            return ps;
        } catch (IOException e) {
            logger.error("Unable to run ADV UI");
            return null;
        }
    }
}
