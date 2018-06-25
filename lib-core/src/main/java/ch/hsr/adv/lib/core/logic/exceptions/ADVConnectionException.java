package ch.hsr.adv.lib.core.logic.exceptions;

import ch.hsr.adv.commons.core.logic.util.ADVException;

/**
 * To be thrown for any exception to do with the connection to the ADV UI.
 */
public class ADVConnectionException extends ADVException {
	
    private static final long serialVersionUID = 1L;

    public ADVConnectionException(String message) {
        super(message);
    }
}
