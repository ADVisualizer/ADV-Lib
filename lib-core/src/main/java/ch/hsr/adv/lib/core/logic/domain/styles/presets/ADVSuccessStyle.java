package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultElementStyle;

/**
 * A predefined style class with a green border.
 */
public class ADVSuccessStyle extends ADVDefaultElementStyle {

    public ADVSuccessStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.GREEN_LIGHT.getColorValue();
        strokeColor = ADVColor.GREEN.getColorValue();
    }
}
