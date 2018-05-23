package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultRelationStyle;

/**
 * A predefined style class with a yellow dotted
 * border.
 */
public class ADVWarningStyle extends ADVDefaultRelationStyle {

    public ADVWarningStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.ORANGE_LIGHT.getColorValue();
        strokeColor = ADVColor.ORANGE.getColorValue();
    }
}
