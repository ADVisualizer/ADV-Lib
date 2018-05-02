package ch.adv.lib.stack.logic;

import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.adv.lib.stack.logic.domain.ADVStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates business logic classes needed to to transform stack data into
 * json to be sent to the ADVCore UI. It is not recommended to override the
 * default methods!
 *
 * @param <T> type of the stack content
 */
public class StackModule<T> implements ADVModule {

    private final ADVStack<T> stack;
    private final String sessionName;
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
        return "stack";
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
