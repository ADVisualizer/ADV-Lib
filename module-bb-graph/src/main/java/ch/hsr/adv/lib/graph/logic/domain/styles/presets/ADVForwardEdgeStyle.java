package ch.hsr.adv.lib.graph.logic.domain.styles.presets;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVColor;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStrokeStyle;
import ch.hsr.adv.commons.core.logic.domain.styles.presets
        .ADVDefaultRelationStyle;

/**
 * Forward edge style
 */
public class ADVForwardEdgeStyle extends ADVDefaultRelationStyle {

    public ADVForwardEdgeStyle() {
        // overwrite fields explicitly instead of getter,
        // because only the fields get serialized
        fillColor = ADVColor.BROWN_LIGHT.getColorValue();
        strokeColor = ADVColor.BROWN.getColorValue();
        strokeStyle = ADVStrokeStyle.DASHED.getStyle();
    }

}
