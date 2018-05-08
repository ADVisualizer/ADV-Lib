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
     * Returns module name
     *
     * @return name of the module
     */
    String getModuleName();

    /**
     * Returns all child modules to be used as auxiliary data structure.
     *
     * @return list of child modules
     */
    List<ADVModule> getChildModules();

}
