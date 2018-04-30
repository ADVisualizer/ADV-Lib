package ch.adv.lib.core.logic.domain.styles;

/**
 * Defines the color of the background or border of an element.
 */
public enum ADVColor {
    STANDARD(0), BLACK(0x000000), WHITE(0xffffff),
    DARKGREY(0x17181a), GREY(0x54575f), LIGHTGREY(0xadb1b8), BLUE(0x085fd1),
    LIGHTBLUE(0x25d6f5), RED(0xd7422c), YELLOW(0xffff00), ORANGE(0xff8e05),
    GREEN(0x51bd84);


    private int colorValue;

    ADVColor(int colorValue) {
        this.colorValue = colorValue;
    }

    /**
     * Returns the enum of the specified string.
     *
     * @param name String representation of the enum value
     * @return the enum
     */
    public static ADVColor byName(String name) {
        return valueOf(name.toUpperCase());
    }

    public int getColorValue() {
        return colorValue;
    }
}
