package ch.adv.lib.array.model;

import ch.adv.lib.logic.model.ADVRelation;
import ch.adv.lib.logic.model.ADVStyle;

/**
 * Represents the order of all elements in an array.
 * Only use this class to be transmitted to the UI.
 */
public class ArrayRelation implements ADVRelation {
    private long sourceElementId;
    private long targetElementId;
    private String label;
    private ADVStyle style;

    public long getSourceElementId() {
        return sourceElementId;
    }

    public void setSourceElementId(long sourceElementId) {
        this.sourceElementId = sourceElementId;
    }

    public long getTargetElementId() {
        return targetElementId;
    }

    public void setTargetElementId(long targetElementId) {
        this.targetElementId = targetElementId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ADVStyle getStyle() {
        return style;
    }

    public void setStyle(ADVStyle style) {
        this.style = style;
    }
}
