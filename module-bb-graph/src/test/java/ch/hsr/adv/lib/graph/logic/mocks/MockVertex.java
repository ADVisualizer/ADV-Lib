package ch.hsr.adv.lib.graph.logic.mocks;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;

public class MockVertex implements ADVVertex<Integer> {
    private Integer value;
    private ADVStyle style;
    private int fixedPosX;
    private int fixedPosY;

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public ADVStyle getStyle() {
        return style;
    }

    public void setStyle(ADVStyle style) {
        this.style = style;
    }

    @Override
    public int getFixedPosX() {
        return fixedPosX;
    }

    public void setFixedPosX(int fixedPosX) {
        this.fixedPosX = fixedPosX;
    }

    @Override
    public int getFixedPosY() {
        return fixedPosY;
    }

    public void setFixedPosY(int fixedPosY) {
        this.fixedPosY = fixedPosY;
    }
}
