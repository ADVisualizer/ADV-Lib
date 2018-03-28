package ch.adv.lib.logic.model.styles;

/**
 * The default implementation of the ADV Style sets standard values for all
 * style variables.
 */
public class ADVDefaultStyle implements ADVStyle {
    @Override
    public String getFillColor() {
        return ADVColor.STANDARD.getColor();
    }

    @Override
    public String getStrokeColor() {
        return ADVColor.STANDARD.getColor();
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
