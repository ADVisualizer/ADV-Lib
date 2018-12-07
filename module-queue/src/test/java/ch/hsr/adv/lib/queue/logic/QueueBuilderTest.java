package ch.hsr.adv.lib.queue.logic;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.ModulePosition;
import ch.hsr.adv.lib.queue.logic.domain.QueueTestModule;
import ch.hsr.adv.lib.queue.logic.domain.TestConstants;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
@RunWith(JukitoRunner.class)
public class QueueBuilderTest {

    @Inject
    private QueueBuilder sut;


    @Test
    public void createModuleGroupTest() {
        // GIVEN
        QueueTestModule queueModule = new QueueTestModule();

        // WHEN
        ModuleGroup queueGroup = sut.build(queueModule);

        // THEN
        assertNotNull(queueGroup);
    }

    @Test
    public void elementBuildTest() {
        // GIVEN
        QueueTestModule queueModule = new QueueTestModule();

        // WHEN
        ModuleGroup qeueueGroup = sut.build(queueModule);

        // THEN
        assertEquals(1, qeueueGroup.getElements().size());

        ADVElement<?> element = qeueueGroup.getElements().get(0);
        assertEquals(0, element.getId());
        assertEquals(TestConstants.ELEMENT_NAME, element.getContent());
        assertNotNull(element.getStyle());

        @SuppressWarnings("rawtypes")
        List<ADVElement> fixedElements = qeueueGroup.getElements().stream()
                .filter(e -> e.getFixedPosX() != 0 || e.getFixedPosY() != 0)
                .collect(Collectors.toList());
        assertEquals(0, fixedElements.size());
    }

    @Test
    public void modulePositionAppendedTest() {
        QueueTestModule queueModule = new QueueTestModule();
        queueModule.setPosition(ModulePosition.LEFT);

        ModuleGroup queueGroup = sut.build(queueModule);

        assertEquals(ModulePosition.LEFT, queueGroup.getPosition());
    }
}