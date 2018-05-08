package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.graph.logic.domain.ADVGraph;
import ch.hsr.adv.lib.graph.logic.domain.ModuleConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates module meta data and graph data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 *
 * @param <V> depends on the vertex content of the graph
 * @param <E> depends on the edge content of the graph
 */
public class GraphModule<V, E> implements ADVModule {

    private final String sessionName;
    private final List<ADVModule> childModules = new ArrayList<>();

    private ADVGraph<V, E> graph;

    public GraphModule(String sessionName, ADVGraph<V, E> graph) {
        this.sessionName = sessionName;
        this.graph = graph;
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

    public void addChildModule(ADVModule childModules) {
        this.childModules.add(childModules);
    }

    public ADVGraph<V, E> getGraph() {
        return graph;
    }

    public void setGraph(ADVGraph<V, E> graph) {
        this.graph = graph;
    }

}
