package ch.hsr.adv.lib.array.logic.domain;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVEnumStyle;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayTestModule implements ArrayModule<String> {

    private final Map<Integer, Coordinate> coordinateMap = new HashMap<>();
    private final Map<Integer, ADVStyle> styleMap = new HashMap<>();
    private final String[] array = new String[2];
    private final String sessionName = "TestSession";
    private boolean showObjectRelations;

    public ArrayTestModule() {
        // create test data
        array[0] = "Test";
        array[1] = "1234";
        coordinateMap.put(0, new Coordinate(0, 0));
        coordinateMap.put(1, new Coordinate(1, 1));
        styleMap.put(0, new ADVEnumStyle());
        styleMap.put(1, new ADVEnumStyle());
    }

    @Override
    public String[] getArray() {
        return array;
    }

    @Override
    public Map<Integer, Coordinate> getCoordinates() {
        return coordinateMap;
    }

    @Override
    public boolean showObjectRelations() {
        return showObjectRelations;
    }

    public void setShowObjectRelations(boolean showObjectRelations) {
        this.showObjectRelations = showObjectRelations;
    }

    @Override
    public Map<Integer, ADVStyle> getStyleMap() {
        return styleMap;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public List<ADVModule> getChildModules() {
        return null;
    }
}
