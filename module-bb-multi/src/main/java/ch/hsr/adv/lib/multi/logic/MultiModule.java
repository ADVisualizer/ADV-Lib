package ch.hsr.adv.lib.multi.logic;


import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Encapsulates module meta data and depth first search algorithm.
 */
public class MultiModule implements ADVModule {

    private final String sessionName;
    private final Map<String, ADVModule> modules = new HashMap<>();

    public MultiModule(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public List<String> getModuleNames() {
        return modules.entrySet().stream().map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public ADVModule getModule(String key) {
        return modules.get(key);
    }

    public void addModule(String key, ADVModule value) {
        modules.put(key, value);
    }
}
