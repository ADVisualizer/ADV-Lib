package ch.adv.lib.logic.model.styles;

/**
 * A predefined style class with a green border.
 */
public class ADVSuccessStyle implements ADVStyle {

    @Override
    public String getFillColor() {
        return ADVColor.STANDARD.getColor();
    }

    @Override
    public String getStrokeColor() {
        return ADVColor.GREEN.getColor();
    }

    @Override
    public String getStrokeStyle() {
        return ADVStrokeStyle.THROUGH.getStyle();
    }

    @Override
    public String getStrokeThickness() {
        return ADVStrokeThickness.STANDARD.getThickness();
    }
}
