package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.commons.array.logic.ConstantsArray;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and array data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 */
public class ArrayModule implements ADVModule {

    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();
    private final List<ADVModule> childModules = new ArrayList<>();
    private final String sessionName;
    private Object[] array;
    private boolean showObjectRelations;

    public ArrayModule(String sessionName, Object[] array) {
        this.sessionName = sessionName;
        this.array = array;
    }


    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ConstantsArray.MODULE_NAME;
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
