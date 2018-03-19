package ch.adv.lib.model;

public interface ADVElement<T> {

    long getElementId();
    //TODO: add field type
    ADVStyle getStyle();
    int getFixedPosX();
    int getFixedPosY();
    T getContent();
}
