package ch.adv.lib.core.logic.domain.styles.presets;

import ch.adv.lib.core.logic.domain.styles.ADVColor;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeStyle;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeThickness;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a lightblue background and a blue dashed
 * border.
 */
public class ADVInfoStyle implements ADVStyle {
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVInfoStyle() {
        this.fillColor = ADVColor.LIGHTBLUE.getColorValue();
        this.strokeColor = ADVColor.BLUE.getColorValue();
        this.strokeThickness = ADVStrokeThickness.THIN.getThickness();
        this.strokeStyle = ADVStrokeStyle.DASHED.getStyle();
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
