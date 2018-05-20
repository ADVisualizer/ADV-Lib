package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.stack.logic.ConstantsStack;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.util.StringUtil;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;

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
    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();

    /**
     * Default constructor
     *
     * @param sessionName name for the stack session
     * @param stack       data structure containing the data
     */
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
        this.sessionName = StringUtil.firstCharToUpper(
                ConstantsStack.MODULE_NAME);
        this.stack = stack;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ConstantsStack.MODULE_NAME;
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

    /**
     * Add a new node style
     *
     * @param id    element id
     * @param style style
     */
    public void addStyle(Integer id, ADVStyle style) {
        getStyleMap().put(id, style);
    }

    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }


    public ADVStack getStack() {
        return stack;
    }


}
