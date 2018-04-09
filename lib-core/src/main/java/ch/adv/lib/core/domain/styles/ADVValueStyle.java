package ch.adv.lib.core.domain.styles;

import ch.adv.lib.core.util.ADVStyleException;

/**
 * ADVStyle implementation that uses 'low level' values.
 */
public class ADVValueStyle implements ADVStyle {
    private int fillColor;
    private int strokeColor;
    private ADVStrokeStyle strokeStyle;
    private int strokeThickness;

    public ADVValueStyle() {
        this(0, null, 0, 0);
    }

    public ADVValueStyle(int strokeColor, ADVStrokeStyle strokeStyle, int
            strokeThickness) {
        this(strokeColor, strokeStyle, strokeThickness, 0);
    }

    public ADVValueStyle(int strokeThicknessColor,
                         ADVStrokeStyle strokeStyle,
                         int strokeThickness, int fillColor) {
        this.strokeColor = strokeThicknessColor;
        this.strokeThickness = strokeThickness;
        this.fillColor = fillColor;
        if (strokeStyle != null) {
            this.strokeStyle = strokeStyle;
        } else {
            this.strokeStyle = ADVStrokeStyle.SOLID;
        }
    }

    @Override
    public int getFillColor() {
        return fillColor;
    }

    /**
     * Set the fill color if it is a valid color. Valid values are between
     * 0x000000 and 0xffffff
     *
     * @param fillColor hex color value to be set
     * @throws ADVStyleException if color is invalid
     */
    public void setFillColor(int fillColor) throws ADVStyleException {
        if (isValidColorValue(fillColor)) {
            this.fillColor = fillColor;
        } else {
            throw new ADVStyleException("Invalid color value. Valid values "
                    + "are between 0x000000 and 0xffffff");
        }
    }

    @Override
    public int getStrokeColor() {
        return strokeColor;
    }

    /**
     * Set the stroke color if it is a valid color. Valid values are between
     * 0x000000 and 0xffffff
     *
     * @param strokeColor hex color value to be set
     * @throws ADVStyleException if color is invalid
     */
    public void setStrokeColor(int strokeColor) throws ADVStyleException {
        if (isValidColorValue(strokeColor)) {
            this.strokeColor = strokeColor;
        } else {
            throw new ADVStyleException("Invalid color value. Valid values "
                    + "are between 0x000000 and 0xffffff");
        }
    }

    @Override
    public String getStrokeStyle() {
        return strokeStyle.getStyle();
    }

    public void setStrokeStyle(ADVStrokeStyle strokeStyle) {
        this.strokeStyle = strokeStyle;
    }

    @Override
    public int getStrokeThickness() {
        return strokeThickness;
    }

    /**
     * Sets the stroke thickness, if it is a valid value.
     *
     * @param strokeThickness to be set
     * @throws ADVStyleException if stroke thickness < 0
     */
    public void setStrokeThickness(int strokeThickness) throws
            ADVStyleException {
        if (strokeThickness >= 0) {
            this.strokeThickness = strokeThickness;
        } else {
            throw new ADVStyleException("Invalid stroke thickness. Please use"
                    + " a value >= 0");
        }

    }

    private boolean isValidColorValue(int colorValue) {
        return colorValue >= 0 && colorValue <= 0xffffff;
    }
}
