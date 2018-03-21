package ch.adv.lib.array.mocks;

import ch.adv.lib.logic.model.ADVStyle;


public class TestStyle implements ADVStyle {
    @Override
    public String getFillColor() {
        return "testColor";
    }

    @Override
    public String getStrokeColor() {
        return "testColor";
    }

    @Override
    public String getStrokeStyle() {
        return "testStyle";
    }

    @Override
    public String getStrokeThickness() {
        return "testThickness";
    }
}
