package ch.adv.lib.util;

import com.google.inject.Inject;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JukitoRunner.class)
public class ClasspathUtilTest {
    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
        }
    }

    @Test
    @Inject
    public void onClassPathGoodCaseTest(ClasspathUtil util) {
        boolean actual = util.onClassPath("ch.adv.lib.util.ClasspathUtilTest");
        assertTrue(actual);
    }

    @Test
    @Inject
    public void onClassPathBadCaseTest(ClasspathUtil util) {
        boolean actual = util.onClassPath("ch.adv.lib.util.Test");
        assertFalse(actual);
    }


}