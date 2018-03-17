package ch.adv.lib;

import ch.adv.lib.model.Session;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Stringifyer {
    @Inject
    private Gson gson;

    public String stringify(Session session){
        return gson.toJson(session);
    }
}
