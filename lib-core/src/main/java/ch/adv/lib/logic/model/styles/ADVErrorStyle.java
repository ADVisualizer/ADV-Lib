package ch.adv.lib.logic.model.styles;

/**
 * A predefined style class with a fat red border.
 */
public class ADVErrorStyle implements ADVStyle {

    @Override
    public String getFillColor() {
        return ADVColor.STANDARD.getColor();
    }

    @Override
    public String getStrokeColor() {
        return ADVColor.RED.getColor();
    }

    @Override
    public String getStrokeStyle() {
        return ADVStrokeStyle.THROUGH.getStyle();
    }

    @Override
    public String getStrokeThickness() {
        return ADVStrokeThickness.FAT.getThickness();
    }
}
