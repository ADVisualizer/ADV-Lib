package ch.hsr.adv.lib.core.logic.util;

import ch.hsr.adv.lib.core.logic.util.ClasspathUtil;
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
        boolean actual = util.onClassPath("ch.hsr.adv.lib.core.logic.util.ClasspathUtilTest");
        assertTrue(actual);
    }

    @Test
    public void onClassPathBadCaseTest() {
        boolean actual = util.onClassPath("ch.hsr.adv.lib.core.logic.util.Test");
        assertFalse(actual);
    }


}