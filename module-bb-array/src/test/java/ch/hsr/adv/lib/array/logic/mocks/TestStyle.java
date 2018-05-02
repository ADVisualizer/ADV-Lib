package ch.hsr.adv.lib.array.logic.mocks;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;


public class TestStyle implements ADVStyle {
    @Override
    public int getFillColor() {
        return 0;
    }

    @Override
    public int getStrokeColor() {
        return 0;
    }

    @Override
    public String getStrokeStyle() {
        return "testStyle";
    }

    @Override
    public int getStrokeThickness() {
        return 0;
    }
}
