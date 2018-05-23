package ch.hsr.adv.lib.core.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets.ADVDefaultStyle;

/**
 * A predefined style class with a lightblue background and a blue dashed
 * border.
 */
public class ADVInfoStyle extends ADVDefaultStyle {

    public ADVInfoStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.BLUE_LIGHT.getColorValue();
        strokeColor = ADVColor.BLUE.getColorValue();
    }

}
