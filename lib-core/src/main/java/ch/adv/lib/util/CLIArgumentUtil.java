package ch.adv.lib.util;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Reads command line arguments and puts them in a map. Code inspired by JavaFX.
 *
 * @author mtrentini
 */
@Singleton
public class CLIArgumentUtil {

    private static final Logger logger = LoggerFactory.getLogger
            (CLIArgumentUtil.class);

    /**
     * This method parses the current array of raw arguments looking for
     * name,value pairs. These name,value pairs are then added to the map
     * for this parameters object, and are of the form: --name=value.
     */
    public Map<String, String> parseNamedParams(String[] args) {
        Map<String, String> namedParams = new HashMap<>();
        for (String arg : args) {
            if (isNamedParam(arg)) {
                final int eqIdx = arg.indexOf('=');
                String key = arg.substring(2, eqIdx);
                String value = arg.substring(eqIdx + 1);
                logger.debug("Found params: {} -> {}", key, value);
                namedParams.put(key, value);
            }
        }
        return namedParams;
    }

    /**
     * Returns true if the specified string is a named parameter of the
     * form: --name=value
     *
     * @param arg the string to check
     * @return true if the string matches the pattern for a named parameter.
     */
    private boolean isNamedParam(String arg) {
        if (arg.startsWith("--")) {
            return (arg.indexOf('=') > 2 && validFirstChar(arg.charAt(2)));
        } else {
            return false;
        }
    }

    /**
     * Validate the first character of a key. It is valid if it is a letter or
     * an "_" character.
     *
     * @param c the first char of a key string
     * @return whether or not it is valid
     */
    private boolean validFirstChar(char c) {
        return Character.isLetter(c) || c == '_';
    }

}
