package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.ArrayElement;
import ch.hsr.adv.lib.array.logic.domain.ModuleConstants;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import com.google.inject.Singleton;


/**
 * Builder Implementation for array module. It builds a ModuleGroup
 * containing the module data. Can only handle array module!
 *
 * @param <T> the type of content of the array
 */
@Singleton
@Module(ModuleConstants.MODULE_NAME)
class ArrayBuilder<T> implements Builder {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    @Override
    public ModuleGroup build(ADVModule advModule) {
        ArrayModule<T> arrayModule = (ArrayModule<T>) advModule;
        ModuleGroup moduleGroup = new ModuleGroup(advModule.getModuleName());

        if (arrayModule.isShowObjectRelations()) {
            moduleGroup.getFlags().add(SHOW_OBJECT_RELATIONS);
        }

        buildElements(arrayModule, moduleGroup);

        return moduleGroup;
    }

    /**
     * Build an element for every index in the array
     *
     * @param arrayModule containing the array data
     * @param arrayGroup  group containing the newly build elements
     */
    private void buildElements(ArrayModule<T> arrayModule,
                               ModuleGroup arrayGroup) {

        T[] array = arrayModule.getArray();
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            ArrayElement e = new ArrayElement();
            e.setId(i);

            if (t != null) {
                e.setContent(t.toString());
            } else {
                if (!arrayModule.isShowObjectRelations()) {
                    e.setContent("null");
                }
            }

            ADVStyle style = arrayModule.getStyleMap().get(i);
            e.setStyle(style);

            arrayGroup.addElement(e);
        }
    }

}
