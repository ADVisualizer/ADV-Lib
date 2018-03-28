package ch.adv.lib.logic.model.styles;

/**
 * A predefined style class with a lightblue background and a blue dashed
 * border.
 */
public class ADVInfoStyle implements ADVStyle {
    @Override
    public String getFillColor() {
        return ADVColor.LIGHTBLUE.getColor();
    }

    @Override
    public String getStrokeColor() {
        return ADVColor.BLUE.getColor();
    }

    @Override
    public String getStrokeStyle() {
        return ADVStrokeStyle.DASHED.getStyle();
    }

    @Override
    public String getStrokeThickness() {
        return ADVStrokeThickness.SLIGHT.getThickness();
    }
}
