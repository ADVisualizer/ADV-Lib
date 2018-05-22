package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.graph.logic.ConstantsGraph;
import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.Stringifyer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle graph module
 * groups!
 */
@Singleton
@Module(ConstantsGraph.MODULE_NAME)
public class GraphStringifyer implements Stringifyer {

    private static final Logger logger = LoggerFactory
            .getLogger(GraphStringifyer.class);

    private final Gson gson;

    @Inject
    public GraphStringifyer(JsonBuilderProvider<GsonBuilder> gsonProvider) {
        this.gson = gsonProvider.getPrettifyer().create();
    }

    @Override
    public JsonElement stringify(ModuleGroup moduleGroup) {
        logger.info("Serialize graph group");
        if (ConstantsGraph.MODULE_NAME.equals(moduleGroup.getModuleName())) {
            String json = gson.toJson(moduleGroup);
            return gson.fromJson(json, JsonElement.class);
        } else {
            return null;
        }
    }
}


