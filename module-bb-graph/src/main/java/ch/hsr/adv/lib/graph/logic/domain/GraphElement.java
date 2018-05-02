package ch.hsr.adv.lib.graph.logic.domain;

import ch.adv.lib.core.logic.domain.ADVElement;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * Object adapter. Maps an ADVVertex to an ADVElement.
 */
public class GraphElement implements ADVElement<String> {
    private long id;
    private String content;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;

    public GraphElement(ADVVertex vertex) {
        //TODO: set id
        this.content = vertex.getValue().toString();
        this.style = vertex.getStyle();
        this.fixedPosX = vertex.getFixedPosX();
        this.fixedPosY = vertex.getFixedPosY();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    @Override
    public int getFixedPosX() {
        return fixedPosX;
    }

    @Override
    public int getFixedPosY() {
        return fixedPosY;
    }

    @Override
    public String getContent() {
        return content;
    }
}
