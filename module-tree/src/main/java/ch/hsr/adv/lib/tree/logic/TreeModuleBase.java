package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for the different tree modules to encapsulate the same parts
 */
public abstract class TreeModuleBase implements ADVModule {
    private final String sessionName;
    private final List<ADVModule> childModules = new ArrayList<>();

    protected TreeModuleBase(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public abstract String getModuleName();

    @Override
    public List<ADVModule> getChildModules() {
        return childModules;
    }
}
