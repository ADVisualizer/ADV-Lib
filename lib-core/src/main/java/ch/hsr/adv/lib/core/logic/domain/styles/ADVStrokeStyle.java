package ch.hsr.adv.lib.core.logic.domain.styles;

/**
 * Defines the style of the stroke or border of an element.
 */
public enum ADVStrokeStyle {

    DOTTED("dotted"), DASHED("dashed"), SOLID("solid"), NONE("none");

    private String style;

    ADVStrokeStyle(String style) {
        this.style = style.toLowerCase();
    }

    /**
     * Returns the enum of the specified string.
     *
     * @param styleName string linked to the enum value
     * @return the enum
     */
    public static ADVStrokeStyle byName(String styleName) {
        return valueOf(styleName.toUpperCase());
    }

    public String getStyle() {
        return style;
    }
}
