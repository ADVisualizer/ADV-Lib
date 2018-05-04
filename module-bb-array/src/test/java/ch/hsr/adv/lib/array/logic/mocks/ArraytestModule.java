package ch.hsr.adv.lib.array.logic.mocks;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.array.logic.domain.Coordinate;
import ch.hsr.adv.lib.core.logic.domain.ADVRelation;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraytestModule implements ArrayModule<Integer> {
    private Integer[] arr = new Integer[2];
    private Map<Integer, ADVStyle> styleMap;
    private Map<Integer, Coordinate> coordsMap;

    public ArraytestModule() {
        arr[1] = 10;
        styleMap = new HashMap<>();
        styleMap.put(1, new TestStyle());
        coordsMap = new HashMap<>();
        coordsMap.put(1, new Coordinate(1, 2));
    }

    @Override
    public Integer[] getArray() {
        return arr;
    }

    @Override
    public Map<Integer, Coordinate> getCoordinates() {
        return coordsMap;
    }

    @Override
    public String getSessionName() {
        return "testSession";
    }

    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }

    @Override
    public boolean showObjectRelations() {
        return false;
    }

}
