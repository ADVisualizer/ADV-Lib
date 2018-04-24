package ch.adv.lib.core.logic.domain.styles.presets;

import ch.adv.lib.core.logic.domain.styles.ADVColor;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeStyle;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeThickness;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a fat red border.
 */
public class ADVErrorStyle implements ADVStyle {
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVErrorStyle() {
        this.fillColor = ADVColor.STANDARD.getColorValue();
        this.strokeColor = ADVColor.RED.getColorValue();
        this.strokeThickness = ADVStrokeThickness.THICK.getThickness();
        this.strokeStyle = ADVStrokeStyle.SOLID.getStyle();
    }

    @Override
    public int getFillColor() {
        return fillColor;
    }

    @Override
    public int getStrokeColor() {
        return strokeColor;
    }

    @Override
    public String getStrokeStyle() {
        return strokeStyle;
    }

    @Override
    public int getStrokeThickness() {
        return strokeThickness;
    }
}
