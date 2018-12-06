package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.ModulePosition;
import ch.hsr.adv.lib.graph.logic.domain.GraphTestModule;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JukitoRunner.class)
public class GraphBuilderTest {

    @Inject
    private GraphBuilder sut;

    @Test
    public void createModuleGroupTest() {
        // GIVEN
        GraphTestModule arrayModule = new GraphTestModule();

        // WHEN
        ModuleGroup arrayGroup = sut.build(arrayModule);

        // THEN
        assertNotNull(arrayGroup);
    }

    @Test
    public void elementBuildTest() {
        // GIVEN
        GraphTestModule arrayModule = new GraphTestModule();

        // WHEN
        ModuleGroup arrayGroup = sut.build(arrayModule);

        // THEN
        assertEquals(2, arrayGroup.getElements().size());

        @SuppressWarnings("rawtypes")
        List<ADVElement> fixedElements = arrayGroup.getElements().stream()
                .filter(e -> e.getFixedPosX() != 0 || e.getFixedPosY() != 0)
                .collect(Collectors.toList());
        assertEquals(0, fixedElements.size());
    }

    @Test
    public void modulePositionAppendedTest() {
        GraphTestModule graphModule = new GraphTestModule();
        graphModule.setPosition(ModulePosition.TOP);

        ModuleGroup graphGroup = sut.build(graphModule);

        assertEquals(ModulePosition.TOP, graphGroup.getPosition());
    }
}