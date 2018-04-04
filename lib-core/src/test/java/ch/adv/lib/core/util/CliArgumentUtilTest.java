package ch.adv.lib.core.util;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class CliArgumentUtilTest {

    @Inject
    private CLIArgumentUtil cli;


    @Test
    public void parseNamedParamGoodCaseTest() {
        String[] params = {"--port=1000", "--version=1"};
        Map<String, String> expected = new HashMap<>();
        expected.put("port", "1000");
        expected.put("version", "1");
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }

    @Test
    public void parseNamedParamsEmptyTest() {
        String[] params = {};
        Map<String, String> expected = new HashMap<>();
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }

    @Test
    public void parseNamedParamsMixedTest() {
        String[] params = {"--port=1000", "--verbose"};
        Map<String, String> expected = new HashMap<>();
        expected.put("port", "1000");
        Map<String, String> actual = cli.parseNamedParams(params);
        assertEquals(expected, actual);
    }
}