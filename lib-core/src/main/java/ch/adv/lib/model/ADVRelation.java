package ch.adv.lib.model;

public interface ADVRelation {

    long getSourceElementId();

    void setSourceElementId(long sourceElementId);

    long getTargetElementId();

    void setTargetElementId(long targetElementId);

    String getLabel();

    void setLabel(String label);

    ADVStyle getStyle();

    void setStyle(ADVStyle style);
}
