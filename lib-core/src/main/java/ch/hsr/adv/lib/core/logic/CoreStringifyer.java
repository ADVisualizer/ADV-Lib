package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CoreStringifyer {

    private final Gson gson;

    @Inject
    public CoreStringifyer(JsonBuilderProvider<GsonBuilder> gsonProvider,
                           ModuleGroupSerializer serializer) {
        GsonBuilder builder = gsonProvider.getPrettifyer();
        builder.registerTypeAdapter(ModuleGroup.class, serializer);
        gson = builder.create();
    }

    /**
     * Builds a json string from an graph session.
     *
     * @param session the session to be transmitted
     * @return json string representation of the session
     */
    public String stringify(final Session session) {
        return gson.toJson(session);
    }
}
