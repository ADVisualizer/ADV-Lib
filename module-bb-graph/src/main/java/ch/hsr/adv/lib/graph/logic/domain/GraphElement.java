package ch.hsr.adv.lib.graph.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Object adapter. Maps an ADVVertex to an ADVElement.
 */
public class GraphElement implements ADVElement<String> {
    private static final transient AtomicInteger ID_COUNTER = new
            AtomicInteger(0);
    private long id;
    private String content;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;

    public GraphElement(ADVVertex vertex) {
        this.id = ID_COUNTER.incrementAndGet();
        if (vertex.getValue() == null) {
            this.content = "";
        } else {
            this.content = vertex.getValue().toString();
        }
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
