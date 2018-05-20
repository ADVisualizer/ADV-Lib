package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeThickness;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a yellow dotted
 * border.
 */
public class ADVWarningStyle implements ADVStyle {
    private static final transient int DARK_YELLOW = 0xf2c540;
    private static final transient int LIGHT_YELLOW = 0xfbefca;
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVWarningStyle() {
        this.fillColor = LIGHT_YELLOW;
        this.strokeColor = DARK_YELLOW;
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
