package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultElementStyle;

/**
 * A predefined style class with a blue border and a lightblue background.
 */
public class ADVInfoStyle extends ADVDefaultElementStyle {

    public ADVInfoStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.BLUE_LIGHT.getColorValue();
        strokeColor = ADVColor.BLUE.getColorValue();
    }

}
