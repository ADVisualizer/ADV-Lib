package ch.adv.lib.core.logic.domain.styles.presets;

import ch.adv.lib.core.logic.domain.styles.ADVColor;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeStyle;
import ch.adv.lib.core.logic.domain.styles.ADVStrokeThickness;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

/**
 * A predefined style class with a fat red border.
 */
public class ADVErrorStyle implements ADVStyle {
    private static final transient int LIGHT_RED = 0xd5b9bb;
    private static final transient int DARK_RED = 0xb95f6a;
    private final int fillColor;
    private final int strokeColor;
    private final int strokeThickness;
    private final String strokeStyle;

    public ADVErrorStyle() {
        this.fillColor = LIGHT_RED;
        this.strokeColor = DARK_RED;
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
