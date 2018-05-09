package ch.hsr.adv.lib.graph.logic.util;

import ch.hsr.adv.lib.core.access.GsonProvider;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;
import ch.hsr.adv.lib.graph.logic.domain.GraphElement;
import ch.hsr.adv.lib.graph.logic.domain.GraphRelation;
import ch.hsr.adv.lib.graph.logic.mocks.MockGraph;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.inject.Inject;

public class MockFactory {

    private ADVVertex<Integer> vertex1;
    private ADVVertex<Integer> vertex2;
    private ADVEdge<Integer> edge;
    @Inject
    private GsonProvider gsonProvider;

    public ModuleGroup getGraphModuleGroup(String moduleName) {

        ModuleGroup moduleGroup = new ModuleGroup(moduleName);

        fillGroup(moduleGroup);

        return moduleGroup;
    }

    public JsonElement getModuleGroupJson(ModuleGroup moduleGroup) {
        String json = gsonProvider.getMinifier().create().toJson(moduleGroup);
        Gson gson = new Gson();
        return gson.fromJson(json, JsonElement.class);
    }

    private void fillGroup(ModuleGroup moduleGroup) {
        MockGraph graph = new MockGraph();
        fillGraph(graph);
        GraphElement element1 = new GraphElement(vertex1);
        moduleGroup.addElement(element1);
        GraphElement element2 = new GraphElement(vertex2);
        moduleGroup.addElement(element2);
        moduleGroup.addRelation(new GraphRelation(edge, element1.getId(),
                element2.getId()));
    }


    public void fillGraph(MockGraph testGraph) {
        vertex1 = testGraph.insertVertex(1);
        vertex2 = testGraph.insertVertex(2);
        edge = testGraph.insertEdge(12, vertex1, vertex2);
    }


}
