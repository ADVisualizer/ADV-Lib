package ch.adv.lib.logic;

import ch.adv.lib.logic.model.Session;


/**
 * Builds a JSON String out of a {@link Session}
 */
public interface Stringifyer {

    /**
     * Builds a json string from a session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    String stringify(Session session);
}
