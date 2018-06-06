package ch.hsr.adv.lib.bootstrapper;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.Stringifyer;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Uses reflection to add module implementations with the {@link Module}
 * annotation to a Guice MapBinder to be injected into the adv ui core.
 * Classes injected this way will be lazily loaded once they are needed.
 *
 * @author mtrentini
 */
public class GuiceBootstrapperModule extends AbstractModule {
    private static final Logger logger = LoggerFactory.getLogger(
            GuiceBootstrapperModule.class);
    private static final String PACKAGE = "ch.hsr.adv.lib";

    /**
     * Finds ADV services annotated with {@link Module} and adds them to the DI
     * container.
     */
    @Override
    protected void configure() {

        MapBinder<String, Stringifyer> stringifyerMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Stringifyer
                .class);

        MapBinder<String, Builder> builderMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Builder.class);

        Reflections reflections = new Reflections(PACKAGE);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(
                Module.class);

        annotated.forEach(instance -> {
            String nameKey = instance.getAnnotation(Module.class).value();

            for (Class<?> clazz : instance.getInterfaces()) {

                if (Stringifyer.class.isAssignableFrom(clazz)) {
                    Class<? extends Stringifyer> stringifyer =
                            (Class<? extends Stringifyer>) instance;
                    stringifyerMapBinder.addBinding(nameKey).to(stringifyer);

                } else if (Builder.class.isAssignableFrom(clazz)) {
                    Class<? extends Builder> builder =
                            (Class<? extends Builder>) instance;
                    builderMapBinder.addBinding(nameKey).to(builder);

                } else {
                    logger.debug("No fitting type found. Type was: {}", clazz);
                }
            }
        });
    }
}
