package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.ADVRelation;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;
import ch.hsr.adv.lib.graph.logic.mocks.MockGraph;
import ch.hsr.adv.lib.graph.logic.util.MockFactory;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class GraphBuilderTest {
    private static final String TEST_DESCRIPTION = "graph snapshot";
    private static final String TEST_SESSION_NAME = "testSession";
    private static final String MODULE_NAME = "graph";
    @Inject
    private GraphBuilder<Integer, Integer> graphBuilderUnderTest;
    @Inject
    private MockFactory factory;
    private MockGraph testGraph;
    private GraphModule<Integer, Integer> testModule;

    @Before
    public void setUp(MockGraph testGraph) {
        testModule = new GraphModule<>(TEST_SESSION_NAME, testGraph);
        this.testGraph = testGraph;
    }


    @Test
    public void buildSessionWithEmptyGraphTest() {
        Session actual = graphBuilderUnderTest.build(testModule,
                TEST_DESCRIPTION);
        assertEquals(MODULE_NAME, actual.getModuleName());
        assertNotNull(actual.getSnapshot());
        assertTrue(0 != actual.getSessionId());
        assertEquals(0, actual.getSnapshot().getElements().size());
        assertEquals(0, actual.getSnapshot().getRelations().size());
        assertEquals(TEST_DESCRIPTION, actual.getSnapshot()
                .getSnapshotDescription());
        assertEquals(TEST_SESSION_NAME, actual.getSessionName());
    }

    @Test
    public void buildSessionWithNormalGraph() {
        factory.fillGraph(testGraph);
        Session actualSession = graphBuilderUnderTest.build(testModule,
                TEST_DESCRIPTION);
        Snapshot actualSnapshot = actualSession.getSnapshot();
        assertEquals(2, actualSnapshot.getElements().size());
        assertEquals(1, actualSnapshot.getRelations().size());
        List<ADVVertex<Integer>> vertices = new ArrayList<>(testGraph.getVertices());
        List<ADVEdge<Integer>> edges = new ArrayList<>(testGraph.getEdges());
        ADVVertex<Integer> vertex1 = vertices.get(0);
        ADVVertex<Integer> vertex2 = vertices.get(1);
        ADVEdge<Integer> edge = edges.get(0);
        assertEquals(vertex1.getValue().toString(), actualSnapshot
                .getElements().get(0)
                .getContent());
        assertEquals(vertex2.getValue().toString(), actualSnapshot
                .getElements().get(1)
                .getContent());
        ADVRelation actualEdge = actualSnapshot.getRelations().get(0);
        assertEquals(edge.getValue().toString(), actualEdge.getLabel());
        assertEquals(actualSnapshot.getElements().get(0).getId(), actualEdge
                .getSourceElementId());
    }

    @Test
    public void buildWrongModuleTest(ADVModule dummyModule) {
        assertNull(dummyModule.getModuleName());
        Session actualSession = graphBuilderUnderTest.build(dummyModule,
                TEST_DESCRIPTION);
        assertNull(actualSession);
    }

}