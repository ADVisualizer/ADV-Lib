package ch.adv.lib.array;

import ch.adv.lib.array.mocks.TestSessionFactory;
import ch.adv.lib.logic.model.ADVElement;
import ch.adv.lib.logic.model.Session;
import ch.adv.lib.logic.model.Snapshot;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
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
    public void stringifyBadTest(){
        testSession.setNames("testmodule", "testSession");
        String actual = stringifyer.stringify(testSession);
        assertEquals(null, actual);
    }

}