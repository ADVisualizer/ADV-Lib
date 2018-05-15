package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.graph.logic.GraphModule;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphTestModule extends GraphModule {

    private final static String SESSION_NAME = "TestSession";

    private final static ADVGraph<ADVVertex, ADVEdge> GRAPH = new ADVGraph<>() {

        @Override
        public Collection<ADVVertex> getVertices() {
            ADVVertex mock1 = Mockito.mock(ADVVertex.class);
            ADVVertex mock2 = Mockito.mock(ADVVertex.class);
            return List.of(mock1, mock2);
        }

        @Override
        public Collection<ADVEdge> getEdges() {
            ADVEdge mock1 = Mockito.mock(ADVEdge.class);
            ADVEdge mock2 = Mockito.mock(ADVEdge.class);

            return List.of(mock1, mock2);
        }

        @Override
        public void addVertex(ADVVertex vertex) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addVertices(ADVVertex... vertices) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addEdge(ADVEdge edge) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addEdges(ADVEdge... edges) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeVertex(ADVVertex vertex) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeEdge(ADVEdge edge) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ADVVertex getVertex(long id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<ADVVertex> getNeighbors(ADVVertex source) {
            throw new UnsupportedOperationException();
        }
    };

    public GraphTestModule() {
        super(SESSION_NAME, GRAPH);
    }
}
