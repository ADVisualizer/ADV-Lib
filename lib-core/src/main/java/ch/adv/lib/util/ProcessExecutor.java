package ch.adv.lib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes a standalone process
 *
 * @author mwieland
 */
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
     * Executes the given class in a standalone process
     *
     * @param mainClassName
     */
    public Process execute(String mainClassName) throws IOException {

        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String javaHome = System.getProperty("java.home");
        String java = javaHome + separator + "bin" + separator + "java";

        String[] command = {java, "-cp", classpath, mainClassName};
        ProcessBuilder builder = createProcessBuilder(command);

        Process process = builder.start();

        if (process.isAlive()) {
            processes.add(process);
        } else {
            logger.warn("Unable to execute main() method of class {}", mainClassName);
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
