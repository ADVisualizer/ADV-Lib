package ch.adv.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClasspathUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClasspathUtil.class);

    /**
     * Checks whether the given class is on the classpath.
     *
     * @param className name of the class to check
     * @return true if the class can be found on classpath
     */
    public boolean onClassPath(String className) {
        try {
            getClass().getClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            logger.error("Class {} not found on the classpath. " +
                    "Please check your project dependencies. Current classpath entries: {}",
                    className,
                    System.getProperty("java.class.path"));
            return false;
        }
    }
}
