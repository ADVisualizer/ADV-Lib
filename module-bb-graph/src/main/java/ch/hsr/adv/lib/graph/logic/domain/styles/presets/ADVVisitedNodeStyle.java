package ch.hsr.adv.lib.graph.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultElementStyle;

/**
 * Visited node style
 */
public class ADVVisitedNodeStyle extends ADVDefaultElementStyle {

    public ADVVisitedNodeStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.GREEN_LIGHT.getColorValue();
        strokeColor = ADVColor.GREEN.getColorValue();
    }
}
