package ch.adv.lib.logic.model;

import ch.adv.lib.logic.model.styles.ADVStyle;

public interface ADVElement<T> {

    long getElementId();
    String getType();
    ADVStyle getStyle();
    int getFixedPosX();
    int getFixedPosY();
    T getContent();
}
