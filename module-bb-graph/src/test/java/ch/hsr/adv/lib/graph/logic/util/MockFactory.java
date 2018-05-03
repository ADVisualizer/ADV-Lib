package ch.hsr.adv.lib.graph.logic.util;

import ch.hsr.adv.lib.core.access.GsonProvider;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;
import ch.hsr.adv.lib.graph.logic.domain.GraphElement;
import ch.hsr.adv.lib.graph.logic.domain.GraphRelation;
import ch.hsr.adv.lib.graph.logic.mocks.MockGraph;
import com.google.inject.Inject;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class MockFactory {
    private ADVVertex<Integer> vertex1;
    private ADVVertex<Integer> vertex2;
    private ADVEdge<Integer> edge;
    @Inject
    private GsonProvider gsonProvider;

    public Session getGraphSession(String moduleName, String sessionName) {
        Session session = new Session();
        session.setNames(moduleName, sessionName);
        Snapshot snapshot = new Snapshot();
        session.setSnapshot(snapshot);

        fillSnapshot(snapshot);

        return session;
    }

    public String getSessionAsString(Session session) {
        return gsonProvider.getMinifier().toJson(session);
    }

    private void fillSnapshot(Snapshot snapshot) {
        MockGraph graph = new MockGraph();
        fillGraph(graph);
        GraphElement element1 = new GraphElement(vertex1);
        snapshot.addElement(element1);
        GraphElement element2 = new GraphElement(vertex2);
        snapshot.addElement(element2);
        snapshot.addRelation(new GraphRelation(edge, element1.getId(),
                element2.getId()));
    }


    public void fillGraph(MockGraph testGraph) {
        vertex1 = testGraph.insertVertex(1);
        vertex2 = testGraph.insertVertex(2);
        edge = testGraph.insertEdge(12, vertex1, vertex2);
    }


}
