package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.commons.array.logic.ConstantsArray;
import ch.hsr.adv.commons.array.logic.domain.ArrayElement;
import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import com.google.inject.Singleton;


/**
 * Builder Strategy for array module. It builds a ModuleGroup
 * containing the module data. Can only handle array module!
 */
@Singleton
@Module(ConstantsArray.MODULE_NAME)
class ArrayBuilder implements Builder {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    @Override
    public ModuleGroup build(ADVModule advModule) {
        ArrayModule arrayModule = (ArrayModule) advModule;
        ModuleGroup moduleGroup = new ModuleGroup(advModule.getModuleName(),
                arrayModule.getPosition());

        if (arrayModule.isShowObjectRelations()) {
            moduleGroup.getFlags().add(SHOW_OBJECT_RELATIONS);
        }

        if (arrayModule.isShowArrayIndices()) {
            moduleGroup.getFlags().add(ConstantsArray.SHOW_ARRAY_INDICES);
        }

        buildElements(arrayModule, moduleGroup);

        return moduleGroup;
    }

    /**
     * Builds an element for every index in the array
     *
     * @param arrayModule containing the array data
     * @param arrayGroup  group containing the newly built elements
     */
    private void buildElements(ArrayModule arrayModule,
                               ModuleGroup arrayGroup) {

        Object[] array = arrayModule.getArray();
        for (int i = 0; i < array.length; i++) {
            Object t = array[i];
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
