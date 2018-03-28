package ch.adv.lib.util;

/**
 * The main exception class to be used for ADV projects.
 */
public class ADVException extends Exception {
    /**
     * Constructs an ADVException with the given message.
     *
     * @param message the error messsage
     */
    public ADVException(String message) {
        super(message);
    }
}
