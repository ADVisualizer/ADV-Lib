package ch.hsr.adv.lib.stack.logic.domain;


import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * Represents the content of one stack index.
 * Only use this class to be transmitted to the UI.
 */
public class StackElement implements ADVElement<String> {
    private long id;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;
    private String content;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    public void setStyle(ADVStyle style) {
        this.style = style;
    }

    @Override
    public int getFixedPosX() {
        return fixedPosX;
    }

    public void setFixedPosX(int fixedPosX) {
        this.fixedPosX = fixedPosX;
    }

    @Override
    public int getFixedPosY() {
        return fixedPosY;
    }

    public void setFixedPosY(int fixedPosY) {
        this.fixedPosY = fixedPosY;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }
}
