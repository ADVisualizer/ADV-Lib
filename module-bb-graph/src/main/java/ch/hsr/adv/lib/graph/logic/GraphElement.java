package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;

/**
 * Represents the content of a graph vertex.
 * Only use this class to be transmitted to the UI!
 * <p>
 * This name adapter is used, so the user can freely choose its class field
 * names.
 * It is important that the field names match. Otherwise the UI would not
 * recognise the fields, as they can be named totally arbitrarily.
 * This is due to the fact, that Gson uses field serialization
 * instead of getter serialization.
 * <p>
 * There is an open pull-request available, which should make this class
 * superfluous.
 * <p>
 * https://github.com/google/gson/pull/1094
 */
public class GraphElement implements ADVElement<String> {

    private long id;
    private String content;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;

    GraphElement(ADVVertex vertex) {
        this.id = vertex.getId();
        if (vertex.getContent() != null) {
            this.content = vertex.getContent().toString();
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
