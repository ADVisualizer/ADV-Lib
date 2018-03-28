package ch.adv.lib.logic.model.styles;

/**
 * Defines the style of the stroke or border of an element.
 */
public enum ADVStrokeStyle {

    DOTTED("dotted"), DASHED("dashed"), THROUGH("through");

    private String style;

    ADVStrokeStyle(String style) {
        this.style = style.toLowerCase();
    }

    public String getStyle() {
        return style;
    }

    public static ADVStrokeStyle byName(String styleName) {
        return valueOf(styleName.toUpperCase());
    }
}
