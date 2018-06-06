package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultElementStyle;

/**
 * A predefined style class with an orange border and light orange background.
 */
public class ADVWarningStyle extends ADVDefaultElementStyle {

    public ADVWarningStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.ORANGE_LIGHT.getColorValue();
        strokeColor = ADVColor.ORANGE.getColorValue();
    }
}
