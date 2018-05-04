package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.Stringifyer;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.Session;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle stack sessions.
 */
@Module("stack")
class StackStringifyer implements Stringifyer {

    public static final String EXPECTED_MODULE = "stack";
    private static final Logger logger = LoggerFactory.getLogger(
            StackStringifyer.class);
    private final JsonBuilderProvider<Gson> gsonProvider;

    @Inject
    StackStringifyer(JsonBuilderProvider<Gson> gsonProvider) {
        this.gsonProvider = gsonProvider;
    }

    /**
     * Builds a json string from a stack session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    @Override
    public String stringify(Session session) {
        if (EXPECTED_MODULE.equals(session.getModuleName())) {

            logger.debug("resulting json: {}", gsonProvider.getPrettifyer()
                    .toJson(session));

            return gsonProvider.getMinifier().toJson(session);
        } else {
            logger.error("Wrong session for this Stringifyer. Module name is "
                    + "{} but should be 'stack'", session.getSessionName());
            return null;
        }
    }
}
