package ch.adv.lib.core.domain.styles.presets;

import ch.adv.lib.core.domain.styles.ADVColor;
import ch.adv.lib.core.domain.styles.ADVStrokeStyle;
import ch.adv.lib.core.domain.styles.ADVStrokeThickness;
import ch.adv.lib.core.domain.styles.ADVStyle;

/**
 * A predefined style class with a yellow dotted
 * border.
 */
public class ADVWarningStyle implements ADVStyle {
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVWarningStyle() {
        this.fillColor = ADVColor.STANDARD.getColorValue();
        this.strokeColor = ADVColor.YELLOW.getColorValue();
        this.strokeThickness = ADVStrokeThickness.THICK.getThickness();
        this.strokeStyle = ADVStrokeStyle.DOTTED.getStyle();
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
