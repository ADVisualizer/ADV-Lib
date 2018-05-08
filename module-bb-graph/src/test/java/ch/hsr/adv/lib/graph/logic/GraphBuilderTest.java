package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.ADVRelation;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JukitoRunner.class)
public class GraphBuilderTest {

    private static final String TEST_SESSION_NAME = "testSession";
    private static final String MODULE_NAME = "graph";
    @Inject
    private GraphBuilder<Integer, Integer> sut;
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
        // WHEN
        ModuleGroup actual = sut.build(testModule);

        // THEN
        assertEquals(MODULE_NAME, actual.getModuleName());
        assertEquals(0, actual.getElements().size());
        assertEquals(0, actual.getRelations().size());
    }

    @Test
    public void buildSessionWithNormalGraph() {
        // GIVEN
        factory.fillGraph(testGraph);

        // WHEN
        ModuleGroup graphGroup = sut.build(testModule);

        // THEN
        assertEquals(2, graphGroup.getElements().size());
        assertEquals(1, graphGroup.getRelations().size());
        List<ADVVertex<Integer>> vertices = new ArrayList<>(testGraph
                .getVertices());
        List<ADVEdge<Integer>> edges = new ArrayList<>(testGraph.getEdges());
        ADVVertex<Integer> vertex1 = vertices.get(0);
        ADVVertex<Integer> vertex2 = vertices.get(1);
        ADVEdge<Integer> edge = edges.get(0);
        assertEquals(vertex1.getValue().toString(), graphGroup
                .getElements().get(0)
                .getContent());
        assertEquals(vertex2.getValue().toString(), graphGroup
                .getElements().get(1)
                .getContent());
        ADVRelation actualEdge = graphGroup.getRelations().get(0);
        assertEquals(edge.getValue().toString(), actualEdge.getLabel());
        assertEquals(graphGroup.getElements().get(0).getId(), actualEdge
                .getSourceElementId());
    }

    @Test
    public void buildWrongModuleTest(ADVModule dummyModule) {
        // GIVEN
        assertNull(dummyModule.getModuleName());

        // WHEN
        ModuleGroup dummyGroup = sut.build(dummyModule);

        // THEN
        assertNull(dummyGroup);
    }

}