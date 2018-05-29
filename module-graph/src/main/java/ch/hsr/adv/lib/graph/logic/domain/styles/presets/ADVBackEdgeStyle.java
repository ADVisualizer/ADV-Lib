package ch.hsr.adv.lib.graph.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultRelationStyle;

/**
 * Back edge style
 */
public class ADVBackEdgeStyle extends ADVDefaultRelationStyle {

    public ADVBackEdgeStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.GREEN_LIGHT.getColorValue();
        strokeColor = ADVColor.GREEN.getColorValue();
        strokeStyle = ADVStrokeStyle.DASHED.getStyle();
    }

}
