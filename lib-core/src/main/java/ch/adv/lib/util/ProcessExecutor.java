package ch.adv.lib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Executes a standalone process
 *
 * @author mwieland
 */
public class ProcessExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ProcessExecutor
            .class);
    private List<Process> processes;

    public ProcessExecutor() {
        processes = new ArrayList<>();
    }

    /**
     * Executes the given class in a standalone process
     *
     * @param mainClassName to be executed
     * @param args          cli arguments
     * @return the executed process
     * @throws IOException if an I/O error occurs
     */
    public Process execute(String mainClassName, String... args) throws
            IOException {

        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String javaHome = System.getProperty("java.home");
        String java = javaHome + separator + "bin" + separator + "java";

        String[] command = {java, "-cp", classpath, mainClassName};

        String[] commandWithArgs = Stream.concat(Arrays.stream(command),
                Arrays.stream(args)).toArray(String[]::new);

        ProcessBuilder builder = createProcessBuilder(commandWithArgs);

        Process process = builder.start();

        if (process.isAlive()) {
            processes.add(process);
        } else {
            logger.warn("Unable to execute main() method of class {}",
                    mainClassName);
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
