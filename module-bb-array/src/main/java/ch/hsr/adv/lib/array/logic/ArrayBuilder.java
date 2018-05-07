package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.ArrayElement;
import ch.hsr.adv.lib.array.logic.domain.Coordinate;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import com.google.inject.Singleton;


/**
 * Builder Implementation for array module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input array.
 *
 * @param <T> the type of content of the array
 */
@Singleton
@Module("array")
class ArrayBuilder<T> implements Builder {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    private ArrayModule<T> module;
    private ModuleGroup moduleGroup = new ModuleGroup();

    /**
     * Builds a session with a snapshot of the array contained in the array
     * module.
     *
     * @param advModule containing the snapshot data
     * @return a session containing the snapshot data
     */
    @Override
    public ModuleGroup build(ADVModule advModule) {
        this.module = (ArrayModule<T>) advModule;

        if (module.showObjectRelations()) {
            moduleGroup.getFlags().add(SHOW_OBJECT_RELATIONS);
        }

        moduleGroup.setModuleName("array");
        buildElements();
        return moduleGroup;
    }

    private void buildElements() {
        T[] array = module.getArray();
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            ArrayElement e = new ArrayElement();
            e.setId(i);

            if (t != null) {
                e.setContent(t.toString());
            } else {
                if (!module.showObjectRelations()) {
                    e.setContent("null");
                }
            }

            ADVStyle style = module.getStyleMap().get(i);
            e.setStyle(style);
            Coordinate cords = module.getCoordinates().get(i);
            if (cords != null) {
                e.setFixedPosX(cords.getX());
                e.setFixedPosY(cords.getY());
            }
            moduleGroup.addElement(e);
        }
    }

}
