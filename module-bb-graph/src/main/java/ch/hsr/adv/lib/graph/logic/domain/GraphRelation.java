package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVRelation;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * Object adapter. Maps an ADVEdge to an ADVRelation.
 */
public class GraphRelation implements ADVRelation<String> {
    private long sourceElementId;
    private long targetElementId;
    private ADVStyle style;
    private String content;
    private boolean isDirected;


    public GraphRelation(ADVEdge edge, long sourceElementId, long
            targetElementId) {
        this.style = edge.getStyle();
        this.content = edge.getValue().toString();
        this.sourceElementId = sourceElementId;
        this.targetElementId = targetElementId;
        this.isDirected = edge.isDirected();
    }

    @Override
    public long getSourceElementId() {
        return sourceElementId;
    }

    @Override
    public long getTargetElementId() {
        return targetElementId;
    }

    @Override
    public String getLabel() {
        return content;
    }

    @Override
    public void setLabel(String label) {
        this.content = label;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    @Override
    public void setStyle(ADVStyle style) {
        this.style = style;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }
}
