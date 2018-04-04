package ch.adv.lib.core.app;

import ch.adv.lib.core.domain.Session;
import ch.adv.lib.core.domain.styles.ADVStyle;
import ch.adv.lib.core.service.Stringifyer;
import ch.adv.lib.core.logic.Builder;

import java.util.Map;

/**
 * Represents a generic module
 */
public interface ADVModule {

    /**
     * Returns the session name, which is displayed as tab label
     *
     * @return session name
     */
    String getSessionName();

    /**
     * Returns the style map
     *
     * @return style map
     */
    Map<Integer, ADVStyle> getStyleMap();

    /**
     * Returns module name
     *
     * @return moduleName
     */
    String getModuleName();

    /**
     * Returns the module specific stringifyer, which serializes module
     * specific objects into json.
     *
     * @return ADV stringifyer
     */
    Stringifyer getStringifyer();

    /**
     * Returns the module specific builder, which creates a
     * {@link Session}.
     *
     * @return ADV builder
     */
    Builder getBuilder();
}
