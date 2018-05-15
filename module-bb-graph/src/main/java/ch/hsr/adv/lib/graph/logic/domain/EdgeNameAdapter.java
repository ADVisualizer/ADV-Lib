package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * This name adapter is used, so the user can freely choose its class field
 * names.
 * It is important that the field names match. Otherwise the UI would not
 * recognise the fields, as they can be named totally arbitrary.
 * This is due to the fact, that guice uses field serialization
 * instead of getter serialization.
 * <p>
 * There is an open pull-request available, which should make this class
 * superfluous.
 * <p>
 * https://github.com/google/gson/pull/1094
 *
 * @param <T> label type
 */
public class EdgeNameAdapter<T> implements ADVEdge<T> {

    private long sourceElementId;
    private long targetElementId;
    private boolean directed;
    private T label;
    private ADVStyle style;

    public EdgeNameAdapter(ADVEdge<T> edge) {
        this.sourceElementId = edge.getSourceElementId();
        this.targetElementId = edge.getTargetElementId();
        this.directed = edge.isDirected();
        this.label = edge.getLabel();
        this.style = edge.getStyle();
    }

    @Override
    public boolean isSelfReference() {
        throw new UnsupportedOperationException();
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
    public T getLabel() {
        return label;
    }

    @Override
    public void setLabel(T label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    @Override
    public void setStyle(ADVStyle style) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void setDirected(boolean directed) {
        throw new UnsupportedOperationException();
    }
}
