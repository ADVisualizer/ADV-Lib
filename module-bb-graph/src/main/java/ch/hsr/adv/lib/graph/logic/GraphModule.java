package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.graph.logic.domain.ADVGraph;

import java.util.List;

/**
 * Encapsulates module meta data and graph data to be sent to the ADVCore UI.
 * It is not recommended to override the default methods!
 *
 * @param <V> depends on the vertex content of the graph
 * @param <E> depends on the edge content of the graph
 */
public class GraphModule<V, E> implements ADVModule {

    private static final String MODULE_NAME = "graph";
    private final String sessionName;
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
    public List<String> getModuleNames() {
        return List.of(MODULE_NAME);
    }

    @Override
    public ADVModule getModule(String moduleName) {
        return this;
    }

    public ADVGraph<V, E> getGraph() {
        return graph;
    }

    public void setGraph(ADVGraph<V, E> graph) {
        this.graph = graph;
    }

}
