package ch.hsr.adv.lib.stack.logic.domain;


import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * Represents the content of one stack element.
 * Only use this class to be transmitted to the UI.
 */
public class StackElement implements ADVElement<String> {
    private static final int DEFAULT_POSITION = 0;
    private long id;
    private ADVStyle style;
    private String content;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    public void setStyle(ADVStyle style) {
        this.style = style;
    }

    /**
     * Fixed positioning of array elements is not supported, because it would
     * be nonsensical.
     *
     * @return the default fixed position
     */
    @Override
    public int getFixedPosX() {
        return DEFAULT_POSITION;
    }

    /**
     * Fixed positioning of array elements is not supported, because it would
     * be nonsensical.
     *
     * @return the default fixed position
     */
    @Override
    public int getFixedPosY() {
        return DEFAULT_POSITION;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
