package ch.adv.lib.array;

import ch.adv.lib.ADVException;
import ch.adv.lib.access.SocketConnector;
import ch.adv.lib.access.Stringifyer;
import ch.adv.lib.model.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle array sessions.
 */
class ArrayStringifyer implements Stringifyer {

    // TODO Builder should be stored and retrieved from the core!
    private final GsonBuilder minifiedBuilder;
    private final GsonBuilder prettyBuilder;

    private static final Logger logger = LoggerFactory.getLogger(SocketConnector.class);

    public ArrayStringifyer() {
        this.prettyBuilder = new GsonBuilder();
        this.prettyBuilder.setPrettyPrinting();

        this.minifiedBuilder = new GsonBuilder();
        this.minifiedBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
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
            logger.debug("resulting json: " + prettyBuilder.create().toJson(session));
            return minifiedBuilder.create().toJson(session);
        } else {
            logger.error("Wrong session for this Stringifyer. Module name is {} but should be 'array'", session.getSessionName());
            return null;
            //TODO: maybe add exception to be thrown
            //throw new ADVException("Wrong module name");
        }


    }
}
