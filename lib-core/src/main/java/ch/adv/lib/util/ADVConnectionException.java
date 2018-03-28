package ch.adv.lib.util;

/**
 * To be thrown for any exception to do with the connection to the ADV UI.
 */
public class ADVConnectionException extends ADVException {

    public ADVConnectionException(String message) {
        super(message);
    }
}
