package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;
import ch.hsr.adv.lib.stack.logic.domain.ModuleConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and stack data to be sent to the ADV UI.
 */
public class StackModule implements ADVModule {

    private final String sessionName;
    private final ADVStack stack;
    private final List<ADVModule> childModules = new ArrayList<>();
    private Map<Integer, ADVStyle> styleMap = new HashMap<>();

    public StackModule(String sessionName, ADVStack stack) {
        this.sessionName = sessionName;
        this.stack = stack;
    }

    /**
     * convenience constructor for multi modules
     *
     * @param stack stack data structure
     */
    public StackModule(ADVStack stack) {
        this.sessionName = Character.toUpperCase(
                ModuleConstants.MODULE_NAME.charAt(0))
                + ModuleConstants.MODULE_NAME.substring(1);
        this.stack = stack;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ModuleConstants.MODULE_NAME;
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

    public ADVStack getStack() {
        return stack;
    }


}
