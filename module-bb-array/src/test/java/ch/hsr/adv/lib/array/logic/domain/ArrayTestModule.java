package ch.hsr.adv.lib.array.logic.domain;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVEnumStyle;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;

import java.util.List;
import java.util.Map;

public class ArrayTestModule extends ArrayModule<String> {

    private final static String SESSION_NAME = "TestSession";

    public ArrayTestModule() {
        super(SESSION_NAME, new String[2]);
        // create test data
        getArray()[0]="Test";
        getArray()[1] = "1234";
        getStyleMap().put(0, new ADVEnumStyle());
        getStyleMap().put(1, new ADVEnumStyle());
    }
}
