package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStrokeThickness;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a green border.
 */
public class ADVSuccessStyle implements ADVStyle {
    private static final transient int DARK_GREEN = 0x6fc383;
    private static final transient int LIGHT_GREEN = 0xd3ecd9;
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVSuccessStyle() {
        this.fillColor = LIGHT_GREEN;
        this.strokeColor = DARK_GREEN;
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
