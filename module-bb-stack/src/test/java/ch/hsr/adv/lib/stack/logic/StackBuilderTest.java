package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.stack.logic.domain.StackTestModule;
import ch.hsr.adv.lib.stack.logic.domain.TestConstants;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JukitoRunner.class)
public class StackBuilderTest {
    @Inject
    private StackBuilder sut;

    @Test
    public void createModuleGroupTest() {
        // GIVEN
        StackTestModule stackModule = new StackTestModule();

        // WHEN
        ModuleGroup stackGroup = sut.build(stackModule);

        // THEN
        assertNotNull(stackGroup);
    }

    @Test
    public void elementBuildTest() {
        // GIVEN
        StackTestModule stackModule = new StackTestModule();

        // WHEN
        ModuleGroup stackGroup = sut.build(stackModule);

        // THEN
        assertEquals(1, stackGroup.getElements().size());

        ADVElement element = stackGroup.getElements().get(0);
        assertEquals(0, element.getId());
        assertEquals(TestConstants.ELEMENT_NAME, element.getContent());
        assertNotNull(element.getStyle());

        List<ADVElement> fixedElements = stackGroup.getElements().stream()
                .filter(e -> e.getFixedPosX() != 0 || e.getFixedPosY() != 0)
                .collect(Collectors.toList());
        assertEquals(0, fixedElements.size());
    }
}