package ch.adv.lib.core.domain.styles;

/**
 * An ADVStyle implementation that uses enums provided by the ADV Lib.
 */
public class ADVEnumStyle implements ADVStyle {

    private ADVColor strokeColor;
    private ADVStrokeStyle strokeStyle;
    private ADVStrokeThickness
            strokeThickness;
    private ADVColor fillColor;

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
            this.strokeColor = strokeThicknessColor;
        } else {
            this.strokeColor = ADVColor.STANDARD;
        }
        if (strokeThickness != null) {
            this.strokeThickness = strokeThickness;
        } else {
            this.strokeThickness = ADVStrokeThickness.STANDARD;
        }
        if (strokeStyle != null) {
            this.strokeStyle = strokeStyle;
        } else {
            this.strokeStyle = ADVStrokeStyle
                    .SOLID;
        }
        if (fillColor != null) {
            this.fillColor = fillColor;
        } else {
            this.fillColor = ADVColor.STANDARD;
        }
    }


    public ADVColor getFillColorEnum() {
        return fillColor;
    }

    public ADVColor getStrokeColorEnum() {
        return strokeColor;
    }

    public void setStrokeColorEnum(ADVColor advStrokeColor) {
        this.strokeColor = advStrokeColor;
    }

    public ADVStrokeStyle getStrokeStyleEnum() {
        return strokeStyle;
    }

    public void setStrokeStyleEnum(ADVStrokeStyle
                                           advStrokeStyle) {
        this.strokeStyle = advStrokeStyle;
    }

    public ADVStrokeThickness getStrokeThicknessEnum() {
        return strokeThickness;
    }

    public void setStrokeThicknessEnum(ADVStrokeThickness
                                               advStrokeThickness) {
        this.strokeThickness = advStrokeThickness;
    }

    @Override
    public int getFillColor() {
        return fillColor.getColorValue();
    }

    public void setFillColor(ADVColor advFillColor) {
        this.fillColor = advFillColor;
    }

    @Override
    public int getStrokeColor() {
        return strokeColor.getColorValue();
    }

    @Override
    public String getStrokeStyle() {
        return strokeStyle.getStyle();
    }

    @Override
    public int getStrokeThickness() {
        return strokeThickness.getThickness();
    }
}
