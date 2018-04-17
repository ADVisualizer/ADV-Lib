package ch.adv.lib.array.domain;

import ch.adv.lib.core.domain.ADVRelation;
import ch.adv.lib.core.domain.styles.ADVStyle;

/**
 * Represents the order of all elements in an array.
 * Only use this class to be transmitted to the UI.
 */
public class ArrayRelation implements ADVRelation {
    private final long sourceElementId;
    private final long targetElementId;
    private String label;
    private ADVStyle style;

    public ArrayRelation(long sourceElementId, long targetElementId) {
        this.sourceElementId = sourceElementId;
        this.targetElementId = targetElementId;
    }

    @Override
    public long getSourceElementId() {
        return sourceElementId;
    }

    @Override
    public long getTargetElementId() {
        return targetElementId;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    @Override
    public void setStyle(ADVStyle style) {
        this.style = style;
    }
}
