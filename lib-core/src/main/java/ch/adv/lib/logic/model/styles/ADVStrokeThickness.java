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

    public String getThickness() {
        return thickness;
    }

    public static ADVStrokeThickness byName(String ticknessName) {
        return valueOf(ticknessName.toUpperCase());
    }
}
