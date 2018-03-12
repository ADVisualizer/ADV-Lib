package ch.adv.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcessExecutor {
    private List<Process> processes;

    private static Logger logger = LoggerFactory.getLogger(ProcessExecutor.class);

    /**
     * Default constructor
     */
    public ProcessExecutor() {
        processes = new ArrayList<>();
    }

    /**
     * Executes jar in a standalone process
     *
     * @param jarName
     */
    public Process executeJarByName(String jarName) throws IOException {
        String[] command = new String[]{"java", "-jar", jarName};
        ProcessBuilder builder = createProcessBuilder(command);
        Process process = builder.start();

        if (process.isAlive()) {

            processes.add(process);

            Optional<ProcessHandle> processHandle = ProcessHandle.of(process.pid());
            if (processHandle.isPresent()) {
                ProcessHandle.Info processInfo = processHandle.get().info();
                logger.info("COMMAND: {}", processInfo.command().orElse(""));
                logger.info("CLI: {}", processInfo.commandLine().orElse(""));
                logger.info("USER: {}", processInfo.user().orElse(""));
                logger.info("CLASSPATH: {}", System.getProperty("java.class.path"));
            }
        } else {
            logger.warn("Unable to execute JAR {}", jarName);
        }

        return process;
    }

    private ProcessBuilder createProcessBuilder(String[] command) {
        return new ProcessBuilder(command);
    }

    /**
     * Kills all executed processes
     */
    public void killAll() {
        processes.forEach(p -> p.destroy());
    }
}
