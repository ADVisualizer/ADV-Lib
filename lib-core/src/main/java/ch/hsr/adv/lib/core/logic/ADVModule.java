package ch.hsr.adv.lib.core.logic;

import java.util.List;

/**
 * Represents a generic module
 */
public interface ADVModule {

    /**
     * Returns the session name, which will be displayed in the ADV UI
     *
     * @return session name
     */
    String getSessionName();

    /**
     * Returns module name: the unique key of this module
     *
     * @return name of the module
     */
    String getModuleName();

    /**
     * Returns all child modules to be used as auxiliary data structures.
     *
     * @return list of child modules
     */
    List<ADVModule> getChildModules();

}
