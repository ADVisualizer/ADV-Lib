package ch.adv.lib.util;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JukitoRunner.class)
public class ClasspathUtilTest {

    @Inject
    private ClasspathUtil util;

    @Test
    public void onClassPathGoodCaseTest() {
        boolean actual = util.onClassPath("ch.adv.lib.util.ClasspathUtilTest");
        assertTrue(actual);
    }

    @Test
    public void onClassPathBadCaseTest() {
        boolean actual = util.onClassPath("ch.adv.lib.util.Test");
        assertFalse(actual);
    }


}