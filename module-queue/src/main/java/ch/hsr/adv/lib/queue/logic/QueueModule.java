package ch.hsr.adv.lib.queue.logic;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.queue.logic.ConstantsQueue;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.util.StringUtil;
import ch.hsr.adv.lib.queue.logic.domain.ADVQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates module meta data and queue data to be sent to the ADV UI.
 */
public class QueueModule implements ADVModule {

    private final String sessionName;
    private final ADVQueue queue;
    private final List<ADVModule> childModules = new ArrayList<>();
    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();

    /**
     * Default constructor
     *
     * @param sessionName name for the queue session
     * @param queue       data structure containing the data
     */
    public QueueModule(String sessionName, ADVQueue queue) {
        this.sessionName = sessionName;
        this.queue = queue;
    }

    /**
     * convenience constructor for multi modules
     *
     * @param queue data structure
     */
    public QueueModule(ADVQueue queue) {
        this.sessionName = StringUtil.firstCharToUpper(
                ConstantsQueue.MODULE_NAME);
        this.queue = queue;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ConstantsQueue.MODULE_NAME;
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


    public ADVQueue getQueue() {
        return queue;
    }


}
