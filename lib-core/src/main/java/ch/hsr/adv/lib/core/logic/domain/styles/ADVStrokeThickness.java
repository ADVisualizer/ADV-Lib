package ch.hsr.adv.lib.core.logic.domain.styles;

/**
 * Defines the thickness of the stroke or border of an element.
 */
public enum ADVStrokeThickness {

    STANDARD(2), THIN(1), MEDIUM(2), THICK(3);

    private int thickness;

    ADVStrokeThickness(int thickness) {
        this.thickness = thickness;
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

    public int getThickness() {
        return thickness;
    }
}
