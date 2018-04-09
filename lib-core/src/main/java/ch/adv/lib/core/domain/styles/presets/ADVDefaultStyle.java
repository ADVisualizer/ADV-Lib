package ch.adv.lib.core.domain.styles.presets;

import ch.adv.lib.core.domain.styles.ADVColor;
import ch.adv.lib.core.domain.styles.ADVStrokeStyle;
import ch.adv.lib.core.domain.styles.ADVStrokeThickness;
import ch.adv.lib.core.domain.styles.ADVStyle;

/**
 * The default implementation of the ADV Style sets standard values for all
 * style variables.
 */
public class ADVDefaultStyle implements ADVStyle {
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVDefaultStyle() {
        this.fillColor = ADVColor.STANDARD.getColorValue();
        this.strokeColor = ADVColor.STANDARD.getColorValue();
        this.strokeThickness = ADVStrokeThickness.STANDARD.getThickness();
        this.strokeStyle = ADVStrokeStyle.NONE.getStyle();
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
