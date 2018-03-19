package ch.adv.lib.array;

import ch.adv.lib.access.SocketConnector;
import ch.adv.lib.access.Stringifyer;
import ch.adv.lib.model.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayStringifyer implements Stringifyer {
    private Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(SocketConnector.class);

    public ArrayStringifyer() {
        gson = new Gson();
    }

    @Override
    public String stringify(Session session) {
        logger.debug("resulting json: " + new GsonBuilder().setPrettyPrinting().create().toJson(session));
        return gson.toJson(session);
    }
}
