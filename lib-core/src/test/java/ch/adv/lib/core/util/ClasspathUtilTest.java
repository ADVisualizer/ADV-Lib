package ch.adv.lib.core.util;

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
        boolean actual = util.onClassPath("ch.adv.lib.core.util.ClasspathUtilTest");
        assertTrue(actual);
    }

    @Test
    public void onClassPathBadCaseTest() {
        boolean actual = util.onClassPath("ch.adv.lib.core.util.Test");
        assertFalse(actual);
    }


}