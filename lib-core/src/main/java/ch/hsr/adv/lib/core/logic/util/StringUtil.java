package ch.hsr.adv.lib.core.logic.util;

/**
 * Utility class for String manipulation
 *
 * @author mwieland
 */
public class StringUtil {

    /**
     * Turns the first char of a String to uppercase
     *
     * @param string string to convert
     * @return converted string or null
     */
    public static String firstCharToUpper(String string) {
        if (string != null && !string.isEmpty()) {
            return Character.toUpperCase(string.charAt(0))
                    + string.substring(1);
        } else {
            return null;
        }
    }
}
