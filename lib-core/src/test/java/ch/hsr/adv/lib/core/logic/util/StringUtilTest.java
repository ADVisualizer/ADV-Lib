package ch.hsr.adv.lib.core.logic.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StringUtilTest {

    @Test
    public void testNormalCase() {
        // GIVEN
        String testString = "helloWorld";
        String expected = "HelloWorld";

        // WHEN
        String result = StringUtil.firstCharToUpper(testString);

        // THEN
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyString() {
        // GIVEN
        String testString = "";

        // WHEN
        String result = StringUtil.firstCharToUpper(testString);

        // THEN
        assertNull(result);
    }

    @Test
    public void testSingleChar() {
        // GIVEN
        String testString = "a";
        String expected = "A";

        // WHEN
        String result = StringUtil.firstCharToUpper(testString);

        // THEN
        assertEquals(expected, result);
    }

    @Test
    public void testAlreadyUpper() {
        // GIVEN
        String testString = "Ab";

        // WHEN
        String result = StringUtil.firstCharToUpper(testString);

        // THEN
        assertEquals(testString, result);
    }
}
