package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and stack data to be sent to the ADV UI.
 *
 * @param <T> type of the stack content
 */
public class StackModule<T> implements ADVModule {

    private static final String MODULE_NAME = "stack";

    private final String sessionName;
    private final ADVStack<T> stack;
    private final List<ADVModule> childModules = new ArrayList<>();
    private Map<Integer, ADVStyle> styleMap = new HashMap<>();

    public StackModule(String sessionName, ADVStack<T> stack) {
        this.sessionName = sessionName;
        this.stack = stack;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public List<ADVModule> getChildModules() {
        return childModules;
    }

    /**
     * Adds an auxiliary module to the graph module
     *
     * @param module child module
     */
    public void addChildModule(ADVModule module) {
        this.childModules.add(module);
    }


    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }

    public void setStyleMap(Map<Integer, ADVStyle> styleMap) {
        this.styleMap = styleMap;
    }

    public ADVStack<T> getStack() {
        return stack;
    }


}
