package ch.adv.lib.array.logic;

import ch.adv.lib.array.logic.mocks.TestSessionFactory;
import ch.adv.lib.core.logic.GuiceCoreModule;
import ch.adv.lib.core.logic.domain.Session;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
@UseModules({GuiceCoreModule.class})
public class ArrayStringifyerTest {

    @Inject
    private ArrayStringifyer stringifyer;

    @Inject
    private TestSessionFactory factory;

    private Session testSession;
    private String expected;

    @Before
    public void setUp() throws Exception {
        testSession = factory.getSession();
        expected = factory.getSessionString();
    }

    @Test
    public void stringifyGoodTest() {
        String actual = stringifyer.stringify(testSession);
        assertEquals(expected, actual);
    }

    @Test
    public void stringifyBadTest() {
        testSession.setNames("testmodule", "testSession");
        String actual = stringifyer.stringify(testSession);
        assertEquals(null, actual);
    }

}