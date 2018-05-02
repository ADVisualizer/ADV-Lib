package ch.adv.lib.stack.logic.domain;

import ch.adv.lib.core.logic.domain.ADVElement;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * Represents the content of one stack index.
 * Only use this class to be transmitted to the UI.
 */
public class StackElement<T> implements ADVElement<T> {
    private long id;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;
    private T content;

    @Override
    public long getElementId() {
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
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }
}
