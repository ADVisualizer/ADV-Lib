package ch.hsr.adv.lib.graph.logic.mocks;

import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVGraph;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;

import java.util.ArrayList;
import java.util.Collection;

public class MockGraph implements ADVGraph<Integer, Integer> {
    private Collection<ADVVertex<Integer>> vertices = new ArrayList<>();
    private Collection<ADVEdge<Integer>> edges = new ArrayList<>();

    @Override
    public ADVVertex<Integer> insertVertex(Integer value) {
        MockVertex vertex = new MockVertex();
        vertex.setValue(value);
        vertices.add(vertex);
        return vertex;
    }

    @Override
    public ADVEdge<Integer> insertEdge(Integer value, ADVVertex<Integer> start,
                                       ADVVertex<Integer> end) {
        MockEdge edge = new MockEdge();
        edge.setValue(value);
        edge.setStartVertex(start);
        edge.setEndVertex(end);
        edges.add(edge);
        return edge;
    }

    @Override
    public void removeVertex(ADVVertex<Integer> vertex) {
        vertices.remove(vertex);
    }

    @Override
    public void removeEdge(ADVEdge edge) {
        edges.remove(edge);
    }

    @Override
    public Collection<ADVVertex<Integer>> getVertices() {
        return vertices;
    }

    @Override
    public Collection<ADVEdge<Integer>> getEdges() {
        return edges;
    }
}
