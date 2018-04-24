package ch.adv.lib.array;

import ch.adv.lib.core.access.JsonBuilderProvider;
import ch.adv.lib.core.logic.Stringifyer;
import ch.adv.lib.core.logic.domain.Session;
import ch.adv.lib.core.access.GsonProvider;
import ch.adv.lib.core.access.SocketConnector;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle array sessions.
 */
class ArrayStringifyer implements Stringifyer {

    private final JsonBuilderProvider<Gson> gsonProvider;
    private static final Logger logger = LoggerFactory.getLogger(
            SocketConnector.class);

    @Inject
    ArrayStringifyer(JsonBuilderProvider<Gson> gsonProvider) {
        this.gsonProvider = gsonProvider;
    }

    /**
     * Builds a json string from an array session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    @Override
    public String stringify(Session session) {
        if (session.getModuleName().equals("array")) {

            logger.debug("resulting json: {}", gsonProvider.getPrettifyer()
                    .toJson(session));

            return gsonProvider.getMinifier().toJson(session);
        } else {
            logger.error("Wrong session for this Stringifyer. Module name is "
                    + "{} but should be 'array'", session.getSessionName());
            return null;
        }


    }
}
