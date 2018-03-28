package ch.adv.lib.logic.model.styles;

/**
 * Defines the thickness of the stroke or border of an element.
 */
public enum ADVStrokeThickness {

    STANDARD("standard"), BOLD("bold"), SLIGHT("slight"), FAT("fat");

    private String thickness;

    ADVStrokeThickness(String thickness) {
        this.thickness = thickness.toLowerCase();
    }

    /**
     * Returns the enum of the specified string.
     *
     * @param ticknessName string linked to the enum value
     * @return the enum
     */
    public static ADVStrokeThickness byName(String ticknessName) {
        return valueOf(ticknessName.toUpperCase());
    }

    public String getThickness() {
        return thickness;
    }
}
