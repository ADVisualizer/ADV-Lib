package ch.adv.lib.core.logic;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Contains a map of all service which need to be implemented by a module.
 * <p>
 * The services are bootstrapped in the bootstrapper and injected by guice.
 */
@Singleton
public class ServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(
            ServiceProvider.class);

    private final Map<String, Builder> builderMap;
    private final Map<String, Stringifyer> stringifyerMap;

    @Inject
    public ServiceProvider(Map<String, Builder> builderMap,
                           Map<String, Stringifyer> stringifyerMap) {

        this.builderMap = builderMap;
        this.stringifyerMap = stringifyerMap;
    }

    /**
     * Returns the parser for the given module
     *
     * @param moduleName name of the module
     * @return parser
     */
    public Builder getBuilder(String moduleName) {
        Builder builder = builderMap.get(moduleName.toLowerCase());
        if (builder == null) {
            logger.error("Parser not found for module {}", moduleName);
        }
        return builder;
    }

    /**
     * Returns the stringifyer for the given module
     *
     * @param moduleName name of the module
     * @return stringifyer
     */
    public Stringifyer getStringifyer(String moduleName) {
        Stringifyer stringifyer = stringifyerMap.get(moduleName.toLowerCase());
        if (stringifyer == null) {
            logger.error("Stringifyer not found for module {}", moduleName);
        }
        return stringifyer;
    }
}