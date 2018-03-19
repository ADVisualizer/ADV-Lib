package ch.adv.lib.array.mocks;

import ch.adv.lib.array.ArrayModule;
import ch.adv.lib.array.Coordinates;
import ch.adv.lib.model.ADVStyle;

import java.util.HashMap;
import java.util.Map;

public class ArraytestModule implements ArrayModule<Integer> {
    private Integer[] arr = new Integer[2];
    private Map<Integer, ADVStyle> styleMap;
    private Map<Integer, Coordinates> coordsMap;

    public ArraytestModule() {
        arr[1] = 10;
        styleMap = new HashMap<>();
        styleMap.put(1, new TestStyle());
        coordsMap = new HashMap<>();
        coordsMap.put(1, new Coordinates(1,2));
    }

    @Override
    public Integer[] getArray() {
        return arr;
    }

    @Override
    public Map<Integer, Coordinates> getCoordinates() {
        return coordsMap;
    }

    @Override
    public String getSessionName() {
        return "testSession";
    }

    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }


}
