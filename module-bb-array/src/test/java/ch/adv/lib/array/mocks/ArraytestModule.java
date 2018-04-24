package ch.adv.lib.array.mocks;

import ch.adv.lib.array.ArrayModule;
import ch.adv.lib.array.domain.Coordinate;
import ch.adv.lib.core.logic.domain.ADVRelation;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraytestModule implements ArrayModule<Integer> {
    private Integer[] arr = new Integer[2];
    private Map<Integer, ADVStyle> styleMap;
    private Map<Integer, Coordinate> coordsMap;
    private List<ADVRelation> relations = new ArrayList<>();

    public ArraytestModule() {
        arr[1] = 10;
        styleMap = new HashMap<>();
        styleMap.put(1, new TestStyle());
        coordsMap = new HashMap<>();
        relations = new ArrayList<>();
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
    public List<ADVRelation> getRelations() {
        return relations;
    }

    @Override
    public boolean showObjectRelations() {
        return false;
    }

}
