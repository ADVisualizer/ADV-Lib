package ch.adv.lib.logic.model.styles;

public class ADVInfoStyle implements ADVStyle {
    @Override
    public String getFillColor() {
        return ADVColor.STANDARD.getColor();
    }

    @Override
    public String getStrokeColor() {
        return ADVColor.LIGHTBLUE.getColor();
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
