package ch.adv.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ADV {
    private static final String ADV_UI_MAIN = "ch.adv.ui.ADVApplication";
    private static final Logger logger = LoggerFactory.getLogger(ADV.class);

    /**
     * Checks whether UI is in classpath, starts the ui process and opens a connection.
     */
    //TODO: think about testability
    public static void launch() {
        ADV adv = new ADV();
        adv.checkDependencies();
        adv.startUI();
        //wait for ADV UI to start listening for connections
        //TODO: find better way to do this
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SocketConnection.connect();
    }

    private void checkDependencies() {
        ClasspathUtil util = new ClasspathUtil();
        boolean onClassPath = util.onClassPath(ADV_UI_MAIN);
        if (!onClassPath) {
            logger.warn("Unable to find ADV UI. Please update your project dependencies or start the UI for yourself. (java -jar adv-ui.jar)");
        }
    }

    private void startUI() {
        try {
            ProcessExecutor processExecutor = new ProcessExecutor();
            processExecutor.execute(ADV_UI_MAIN);
        } catch (IOException e) {
            logger.error("Unable to launch standalone process");
        }
    }

    public static void snapshot(ADVModule module) {
        // transmit module to ADV UI
        SocketConnection.send(module.toString());
        module.getStyleMap().values().forEach((advStyle -> SocketConnection.send(advStyle.toString())));
    }

    public static void disconnect(){
        SocketConnection.disconnect();
    }
}
