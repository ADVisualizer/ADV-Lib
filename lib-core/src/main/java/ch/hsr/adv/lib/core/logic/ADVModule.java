package ch.hsr.adv.lib.core.logic;

import java.util.List;

/**
 * Represents a generic module
 */
public interface ADVModule {

    /**
     * Returns the session name, which is displayed as tab label
     *
     * @return session name
     */
    String getSessionName();

    /**
     * Returns all module names
     *
     * @return module names
     */
    List<String> getModuleNames();

    /**
     * Returns the correct module
     *
     * @param moduleName module name
     * @return module with the given name
     */
    ADVModule getModule(String moduleName);
}
