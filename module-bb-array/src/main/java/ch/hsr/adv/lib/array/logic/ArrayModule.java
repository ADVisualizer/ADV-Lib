package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.ModuleConstants;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and array data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 *
 * @param <T> depends on the content of the array
 */
public class ArrayModule<T> implements ADVModule {

    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();
    private final List<ADVModule> childModules = new ArrayList<>();
    private final String sessionName;
    private T[] array;
    private boolean showObjectRelations;

    public ArrayModule(String sessionName, T[] array) {
        this.sessionName = sessionName;
        this.array = array;
    }


    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
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
     * @return whether to show the independent objects or not
     */
    public boolean isShowObjectRelations() {
        return showObjectRelations;
    }

    /**
     * Display array content as independent objects
     *
     * @param showObjectRelations wheter or not object references should be
     *                            displayed
     */
    public void setShowObjectRelations(boolean showObjectRelations) {
        this.showObjectRelations = showObjectRelations;
    }

    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }

}
