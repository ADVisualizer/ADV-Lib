package ch.adv.lib.core.logic;

import ch.adv.lib.core.logic.domain.Session;


/**
 * Builds a JSON String out of a {@link Session}
 * <p>
 * Abstraction Interface of the strategy pattern. Every Module supplies a
 * concrete strategy to be used.
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
