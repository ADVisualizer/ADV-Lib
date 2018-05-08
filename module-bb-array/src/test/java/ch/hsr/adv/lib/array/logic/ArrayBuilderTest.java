package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.ArrayTestModule;
import ch.hsr.adv.lib.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JukitoRunner.class)
public class ArrayBuilderTest {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    @Inject
    private ArrayBuilder sut;

    @Test
    public void addFlagsTest() {
        // GIVEN
        ArrayTestModule arrayModule = new ArrayTestModule();
        arrayModule.setShowObjectRelations(true);

        // WHEN
        ModuleGroup arrayGroup = sut.build(arrayModule);

        // THEN
        assertTrue(arrayGroup.getFlags().contains(SHOW_OBJECT_RELATIONS));
    }

    @Test
    public void createModuleGroupTest() {
        // GIVEN
        ArrayTestModule arrayModule = new ArrayTestModule();

        // WHEN
        ModuleGroup arrayGroup = sut.build(arrayModule);

        // THEN
        assertNotNull(arrayGroup);
    }

    @Test
    public void elementBuildTest() {
        // GIVEN
        ArrayTestModule arrayModule = new ArrayTestModule();

        // WHEN
        ModuleGroup arrayGroup = sut.build(arrayModule);

        // THEN
        assertEquals(2, arrayGroup.getElements().size());

        for (int i = 0; i < arrayGroup.getElements().size(); i++) {
            ADVElement element = arrayGroup.getElements().get(i);
            assertEquals(i, element.getId());
            assertEquals(i, element.getFixedPosX());
            assertEquals(i, element.getFixedPosY());
            assertNotNull(element.getContent());
            assertNotNull(element.getStyle());
            assertNotNull(element.getStyle());
        }
    }




}