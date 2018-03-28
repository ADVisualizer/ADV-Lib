package ch.adv.lib.logic.model.styles;

/**
 * Defines the color of the background or border of an element.
 */
public enum ADVColor {
    STANDARD("standard"), BLACK("black"), WHITE("white"), DARKGREY(
            "darkgrey"), GREY("grey"), LIGHTGREY("lightgrey"), BLUE(
            "blue"), LIGHTBLUE("lightblue"), RED(
            "red"), YELLOW("yellow"), ORANGE("orange"), GREEN("green");

    private String color;

    ADVColor(String color) {
        this.color = color.toLowerCase();
    }

    public String getColor() {
        return color;
    }

    /**
     * Returns the enum of the specified string.
     *
     * @param colorName string linked to the enum value
     * @return the enum
     */
    public static ADVColor byName(String colorName) {
        return valueOf(colorName.toUpperCase());
    }
}
