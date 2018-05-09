package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.GuiceCoreModule;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.graph.logic.domain.ModuleConstants;
import ch.hsr.adv.lib.graph.logic.util.MockFactory;
import com.google.gson.JsonElement;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JukitoRunner.class)
@UseModules(GuiceCoreModule.class)
public class GraphStringifyerTest {
    private static final String WRONG_MODULE_NAME = "array";

    @Inject
    private MockFactory factory;

    @Inject
    private GraphStringifyer sut;

    @Test
    public void stringifyTest() {
        // GIVEN
        ModuleGroup moduleGroup = factory.getGraphModuleGroup(
                ModuleConstants.MODULE_NAME);
        JsonElement expected = factory.getModuleGroupJson(moduleGroup);

        // WHEN
        JsonElement actual = sut.stringify(moduleGroup);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void stringifyWrongModuleTest() {
        // GIVEN
        ModuleGroup moduleGroup = factory.getGraphModuleGroup
                (WRONG_MODULE_NAME);

        // WHEN
        JsonElement actual = sut.stringify(moduleGroup);

        // THEN
        assertNull(actual);
    }
}