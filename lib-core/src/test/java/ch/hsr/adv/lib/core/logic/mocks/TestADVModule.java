package ch.hsr.adv.lib.core.logic.mocks;

import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.ArrayList;
import java.util.List;

public class TestADVModule implements ADVModule {
    private ArrayList<ADVModule> childModules = new ArrayList<>();
    @Override
    public String getSessionName() {
        return TestConstants.SESSION_NAME;
    }

    @Override
    public String getModuleName() {
        return TestConstants.MODULE_NAME;
    }

    @Override
    public List<ADVModule> getChildModules() {
        return childModules;
    }
}
