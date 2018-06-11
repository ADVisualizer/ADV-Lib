package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultElementStyle;

/**
 * A predefined style class with a red border and a light red background.
 */
public class ADVErrorStyle extends ADVDefaultElementStyle {

    public ADVErrorStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.RED_LIGHT.getColorValue();
        strokeColor = ADVColor.RED.getColorValue();
    }

}
