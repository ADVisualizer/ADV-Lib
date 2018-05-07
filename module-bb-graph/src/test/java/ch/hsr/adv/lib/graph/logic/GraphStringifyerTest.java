package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.GuiceCoreModule;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.graph.logic.util.MockFactory;
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
    private static final String MODULE_NAME = "graph";
    private static final String WRONG_MODULE_NAME = "array";
    private static final String SESSION_NAME = "test";
    @Inject
    private MockFactory factory;

    @Inject
    private GraphStringifyer sut;

    @Test
    public void stringifyTest() {
        // GIVEN
        Session session = factory.getGraphSession(MODULE_NAME, SESSION_NAME);
        String expected = factory.getSessionAsString(session);

        // WHEN
        String actual = sut.stringify(session);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    public void stringifyWrongModuleTest() {
        // GIVEN
        Session session = factory.getGraphSession(
                WRONG_MODULE_NAME, SESSION_NAME);

        // WHEN
        String actual = sut.stringify(session);

        // THEN
        assertNull(actual);
    }
}