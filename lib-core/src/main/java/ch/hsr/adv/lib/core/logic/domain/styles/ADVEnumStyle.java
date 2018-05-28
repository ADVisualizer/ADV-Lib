package ch.hsr.adv.lib.core.logic.domain.styles;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeThickness;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;

/**
 * An ADVStyle implementation that uses enums provided by the ADVCore Lib.
 */
public class ADVEnumStyle implements ADVStyle {

    private int fillColor;
    private int strokeColor;
    private String strokeStyle;
    private double strokeThickness;

    public ADVEnumStyle() {
        this(null, null, null, null);
    }

    public ADVEnumStyle(ADVColor strokeColor,
                        ADVStrokeStyle strokeStyle,
                        ADVStrokeThickness strokeThickness) {
        this(null, strokeColor, strokeStyle, strokeThickness);
    }

    public ADVEnumStyle(ADVColor fillColor,
                        ADVColor strokeColor,
                        ADVStrokeStyle strokeStyle,
                        ADVStrokeThickness strokeThickness) {

        if (strokeColor != null) {
            this.strokeColor = strokeColor.getColorValue();
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
    public double getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(ADVStrokeThickness advStrokeThickness) {
        this.strokeThickness = advStrokeThickness.getThickness();
    }
}
