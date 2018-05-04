package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.Stringifyer;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.Session;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle graph sessions.
 */
@Singleton
@Module("graph")
public class GraphStringifyer implements Stringifyer {
    private static final String EXPECTED_MODULE = "graph";
    private static final Logger logger = LoggerFactory.getLogger(
            GraphStringifyer.class);
    private final JsonBuilderProvider<Gson> gsonProvider;

    @Inject
    GraphStringifyer(JsonBuilderProvider<Gson> gsonProvider) {
        this.gsonProvider = gsonProvider;
    }

    /**
     * Builds a json string from a graph session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    @Override
    public String stringify(Session session) {
        if (EXPECTED_MODULE.equals(session.getModuleName())) {

            logger.info("resulting json: {}", gsonProvider.getPrettifyer()
                    .toJson(session));

            return gsonProvider.getMinifier().toJson(session);
        } else {
            logger.error("Wrong session for this Stringifyer. Module name is "
                    + "{} but should be 'graph'", session.getSessionName());
            return null;
        }


    }
}


