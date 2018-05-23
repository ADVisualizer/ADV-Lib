package ch.hsr.adv.lib.graph.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.presets.ADVDefaultLineStyle;

public class ADVDiscoveryEdgeStyle extends ADVDefaultLineStyle {

    public ADVDiscoveryEdgeStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.BLUE_LIGHT.getColorValue();
        strokeColor = ADVColor.BLUE.getColorValue();
    }

}
