package ch.hsr.adv.lib.graph.logic.mocks;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;

public class MockEdge implements ADVEdge<Integer> {
    private boolean isDirected;
    private ADVVertex startVertex;
    private ADVVertex endVertex;
    private Integer value;
    private ADVStyle style;

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    @Override
    public ADVVertex getStarVertex() {
        return startVertex;
    }

    @Override
    public ADVVertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(ADVVertex endVertex) {
        this.endVertex = endVertex;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    public void setStyle(ADVStyle style) {
        this.style = style;
    }

    public void setStartVertex(ADVVertex startVertex) {
        this.startVertex = startVertex;
    }
}
