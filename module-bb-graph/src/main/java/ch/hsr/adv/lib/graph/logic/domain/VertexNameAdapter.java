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
public class VertexNameAdapter<T> implements ADVVertex<T> {

    private long id;
    private T content;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;
    private int inDegree;
    private int outDegree;

    public VertexNameAdapter(ADVVertex<T> vertex) {
        this.id = vertex.getId();
        this.content = vertex.getContent();
        this.style = vertex.getStyle();
        this.fixedPosX = vertex.getFixedPosX();
        this.fixedPosY = vertex.getFixedPosY();
        this.inDegree = vertex.getInDegree();
        this.outDegree = vertex.getOutDegree();
    }

    @Override
    public int getOutDegree() {
        return outDegree;
    }

    @Override
    public int getInDegree() {
        return inDegree;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
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
    public int getFixedPosX() {
        return fixedPosX;
    }

    @Override
    public void setFixedPosX(int fixedPosX) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFixedPosY() {
        return fixedPosY;
    }

    @Override
    public void setFixedPosY(int fixedPosY) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public void setContent(T content) {
        throw new UnsupportedOperationException();
    }

}
