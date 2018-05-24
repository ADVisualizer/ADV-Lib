package ch.hsr.adv.lib.graph.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultRelationStyle;

/**
 * Discovery edge style
 */
public class ADVDiscoveryEdgeStyle extends ADVDefaultRelationStyle {

    public ADVDiscoveryEdgeStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.BLUE_LIGHT.getColorValue();
        strokeColor = ADVColor.BLUE.getColorValue();
    }

}
