package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.commons.graph.logic.ConstantsGraph;
import ch.hsr.adv.lib.core.logic.GuiceCoreModule;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import com.google.gson.JsonElement;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(JukitoRunner.class)
@UseModules( {GuiceCoreModule.class})
public class GraphStringifyerTest {

    @Inject
    private GraphStringifyer sut;

    @Test
    public void stringifyTest() {
        // GIVEN
        ModuleGroup moduleGroup = new ModuleGroup(ConstantsGraph.MODULE_NAME);

        // WHEN
        JsonElement element = sut.stringify(moduleGroup);

        // THEN
        assertNotNull(element);
    }


    @Test
    public void stringifyBadTest() {
        // GIVEN
        ModuleGroup moduleGroup = new ModuleGroup("wrong");

        // WHEN
        JsonElement element = sut.stringify(moduleGroup);

        // THEN
        assertNull(element);
    }

}