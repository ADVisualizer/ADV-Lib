package ch.adv.lib.core.domain.styles;

/**
 * Defines the style of the stroke or border of an element.
 */
public enum ADVStrokeStyle {

    DOTTED("dotted"), DASHED("dashed"), THROUGH("through");

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
