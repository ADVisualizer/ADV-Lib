package ch.adv.lib.util;

import com.google.inject.Inject;
import org.jukito.All;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class CliArgumentUtilTest {
    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
        }
    }

    @Test
    @Inject
    public void parseNamedParamGoodCaseTest(CLIArgumentUtil cli) {
        String[] params = {"--port=1000", "--version=1"};
        Map<String, String> expected = new HashMap<>();
        expected.put("port", "1000");
        expected.put("version", "1");
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }

    @Test
    @Inject
    public void parseNamedParamsEmptyTest(CLIArgumentUtil cli) {
        String[] params = {};
        Map<String, String> expected = new HashMap<>();
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }

    @Test
    @Inject
    public void parseNamedParamsMixedTest(CLIArgumentUtil cli) {
        String[] params = {"--port=1000", "--verbose"};
        Map<String, String> expected = new HashMap<>();
        expected.put("port", "1000");
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }
}