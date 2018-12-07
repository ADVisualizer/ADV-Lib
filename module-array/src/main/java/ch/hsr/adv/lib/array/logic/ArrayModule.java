package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.commons.array.logic.ConstantsArray;
import ch.hsr.adv.commons.core.logic.domain.ModulePosition;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.queue.logic.ConstantsQueue;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and array data to be sent to the ADV UI.
 */
public class ArrayModule implements ADVModule {

    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();
    private final List<ADVModule> childModules = new ArrayList<>();
    private final String sessionName;
    private Object[] array;
    private boolean showObjectRelations;
    private boolean showArrayIndices;
    private ModulePosition position = ModulePosition.DEFAULT;

    /**
     * Default constructor
     *
     * @param sessionName name for the array session
     * @param array       data structure containing the data
     */
    public ArrayModule(String sessionName, Object[] array) {
        this.sessionName = sessionName;
        this.array = array;
    }

    /**
     * Convenience constructor without a session name. Can be used for Child
     * Module instantiation.
     *
     * @param array data structure
     */
    public ArrayModule(Object[] array) {
        this.sessionName = StringUtil.firstCharToUpper(
                ConstantsQueue.MODULE_NAME);
        this.array = array;
    }

    public Object[] getArray() {
        return array;
    }

    /**
     * Sets a new array as module data. To be used to <em>grow</em> the array
     *
     * @param array new array to be set
     */
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
     * @return whether or not to object references should be displayed
     */
    public boolean isShowObjectRelations() {
        return showObjectRelations;
    }

    /**
     * Display object references
     *
     * @param showObjectRelations whether or not object references should be
     *                            displayed
     */
    public void setShowObjectRelations(boolean showObjectRelations) {
        this.showObjectRelations = showObjectRelations;
    }

    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }

    public void setShowArrayIndices(boolean showArrayIndices) {
        this.showArrayIndices = showArrayIndices;
    }

    public boolean isShowArrayIndices() {
        return showArrayIndices;
    }

    public ModulePosition getPosition() {
        return position;
    }

    public void setPosition(ModulePosition position) {
        this.position = position;
    }
}
