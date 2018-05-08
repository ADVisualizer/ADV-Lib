package ch.hsr.adv.lib.array.logic;

import ch.hsr.adv.lib.array.logic.domain.ModuleConstants;
import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.Stringifyer;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a json string from the input session. Can only handle array sessions.
 */
@Singleton
@Module(ModuleConstants.MODULE_NAME)
public class ArrayStringifyer implements Stringifyer {

    private static final Logger logger = LoggerFactory
            .getLogger(ArrayStringifyer.class);
    private final Gson gson;

    @Inject
    public ArrayStringifyer(JsonBuilderProvider<GsonBuilder> gsonProvider) {
        this.gson = gsonProvider.getPrettifyer().create();
    }

    /**
     * Builds a json string from an array module group.
     *
     * @param moduleGroup the moduleGroup to be transmitted
     * @return json string representation of the session
     */
    @Override
    public JsonElement stringify(ModuleGroup moduleGroup) {
        logger.info("Serialize array group");
        if (ModuleConstants.MODULE_NAME.equals(moduleGroup.getModuleName())) {
            String json = gson.toJson(moduleGroup);
            return gson.fromJson(json, JsonElement.class);
        } else {
            return null;
        }
    }
}
