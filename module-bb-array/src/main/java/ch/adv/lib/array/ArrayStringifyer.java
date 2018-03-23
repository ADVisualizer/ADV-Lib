package ch.adv.lib.array;

import ch.adv.lib.service.GsonProvider;
import ch.adv.lib.service.SocketConnector;
import ch.adv.lib.logic.Stringifyer;
import ch.adv.lib.logic.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle array sessions.
 */
class ArrayStringifyer implements Stringifyer {

    private final GsonProvider gsonProvider;
    private static final Logger logger = LoggerFactory.getLogger
            (SocketConnector.class);

    public ArrayStringifyer() {
        gsonProvider = new GsonProvider();
    }

    /**
     * Builds a json string from an array session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    @Override
    public String stringify(Session session) {
        if (session.getModule().equals("array")) {
            logger.debug("resulting json: " + gsonProvider
                    .getPrettifyer().toJson(session));
            return gsonProvider.getMinifier().toJson
                    (session);
        } else {
            logger.error("Wrong session for this Stringifyer. Module name is " +
                    "{} but should be 'array'", session.getSessionName());
            return null;
        }


    }
}
