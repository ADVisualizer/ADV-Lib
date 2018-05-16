package ch.hsr.adv.lib.core.logic.util;

/**
 * Utility methods for String manipulation
 *
 * @author mwieland
 */
public class StringUtil {

    /**
     * Turn the first char of a String to uppercase
     *
     * @param string string to convert
     * @return converted string or null
     */
    public static final String firstCharToUpper(String string) {
        if (string != null && !string.isEmpty()) {
            return Character.toUpperCase(string.charAt(0))
                    + string.substring(1);
        } else {
            return null;
        }
    }
}
