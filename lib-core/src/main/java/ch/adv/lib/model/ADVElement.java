package ch.adv.lib.model;

public interface ADVElement<T> {

    long getElementId();
    //TODO: add field type?
    ADVStyle getStyle();
    //TODO: how to make this optional? -> ExclusionStrategy?
    int getFixedPosX();
    int getFixedPosY();
    T getContent();
}
