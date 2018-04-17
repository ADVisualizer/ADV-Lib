package ch.adv.lib.core.domain.styles;

/**
 * An ADVStyle implementation that uses enums provided by the ADV Lib.
 */
public class ADVEnumStyle implements ADVStyle {

    private int strokeColor;
    private String strokeStyle;
    private int
            strokeThickness;
    private int fillColor;

    public ADVEnumStyle() {
        this(null, null, null, null);
    }

    public ADVEnumStyle(ADVColor strokeColor,
                        ADVStrokeStyle strokeStyle,
                        ADVStrokeThickness strokeThickness) {
        this(strokeColor, strokeStyle, strokeThickness, null);
    }

    public ADVEnumStyle(ADVColor strokeThicknessColor,
                        ADVStrokeStyle strokeStyle,
                        ADVStrokeThickness strokeThickness,
                        ADVColor fillColor) {
        if (strokeThicknessColor != null) {
            this.strokeColor = strokeThicknessColor.getColorValue();
        } else {
            this.strokeColor = ADVColor.STANDARD.getColorValue();
        }
        if (strokeThickness != null) {
            this.strokeThickness = strokeThickness.getThickness();
        } else {
            this.strokeThickness = ADVStrokeThickness.STANDARD.getThickness();
        }
        if (strokeStyle != null) {
            this.strokeStyle = strokeStyle.getStyle();
        } else {
            this.strokeStyle = ADVStrokeStyle.SOLID.getStyle();
        }
        if (fillColor != null) {
            this.fillColor = fillColor.getColorValue();
        } else {
            this.fillColor = ADVColor.STANDARD.getColorValue();
        }
    }

    @Override
    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(ADVColor advFillColor) {
        this.fillColor = advFillColor.getColorValue();
    }

    @Override
    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(ADVColor advStrokeColor) {
        this.strokeColor = advStrokeColor.getColorValue();
    }

    @Override
    public String getStrokeStyle() {
        return strokeStyle;
    }

    public void setStrokeStyle(ADVStrokeStyle
                                       advStrokeStyle) {
        this.strokeStyle = advStrokeStyle.getStyle();
    }

    @Override
    public int getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(ADVStrokeThickness
                                           advStrokeThickness) {
        this.strokeThickness = advStrokeThickness.getThickness();
    }
}
