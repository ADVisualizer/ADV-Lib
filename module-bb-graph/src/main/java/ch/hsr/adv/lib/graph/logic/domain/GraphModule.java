package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.commons.graph.logic.ConstantsGraph;
import ch.hsr.adv.commons.graph.logic.domain.ADVEdge;
import ch.hsr.adv.commons.graph.logic.domain.ADVGraph;
import ch.hsr.adv.commons.graph.logic.domain.ADVVertex;
import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates module meta data and graph data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 */
public class GraphModule implements ADVModule {

    private final String sessionName;
    private final List<ADVModule> childModules = new ArrayList<>();

    private ADVGraph<? extends ADVVertex, ? extends ADVEdge> graph;

    public GraphModule(String sessionName, ADVGraph<? extends ADVVertex, ?
            extends ADVEdge> graph) {
        this.sessionName = sessionName;
        this.graph = graph;
    }

    /**
     * convenience constructor for multi modules
     *
     * @param graph graph data structure
     */
    public GraphModule(ADVGraph<? extends ADVVertex, ? extends ADVEdge> graph) {
        this.sessionName = Character.toUpperCase(
                ConstantsGraph.MODULE_NAME.charAt(0))
                + ConstantsGraph.MODULE_NAME.substring(1);
        this.graph = graph;
    }

    public ADVGraph<? extends ADVVertex, ? extends ADVEdge> getGraph() {
        return graph;
    }

    public void setGraph(ADVGraph<? extends ADVVertex, ? extends ADVEdge>
                                 graph) {
        this.graph = graph;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ConstantsGraph.MODULE_NAME;
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

}
