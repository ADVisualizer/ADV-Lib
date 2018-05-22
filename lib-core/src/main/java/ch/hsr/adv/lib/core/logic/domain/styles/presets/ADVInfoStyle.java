package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeThickness;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a lightblue background and a blue dashed
 * border.
 */
public class ADVInfoStyle implements ADVStyle {
    private static final transient int DARK_BLUE = 0x64c0ce;
    private static final transient int LIGHT_BLUE = 0xcfeaef;
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVInfoStyle() {
        this.fillColor = LIGHT_BLUE;
        this.strokeColor = DARK_BLUE;
        this.strokeThickness = ADVStrokeThickness.STANDARD.getThickness();
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
