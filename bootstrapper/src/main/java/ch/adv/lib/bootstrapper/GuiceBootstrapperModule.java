package ch.adv.lib.bootstrapper;

import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.logic.Stringifyer;
import ch.adv.lib.core.logic.domain.Module;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
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
    private static final String PACKAGE = "ch.adv.lib";

    @Override
    protected void configure() {

        MapBinder<String, Stringifyer> stringifyerMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Stringifyer
                .class);

        MapBinder<String, Builder> builderMapBinder
                = MapBinder.newMapBinder(binder(), String.class, Builder.class);


        Reflections reflections = new Reflections(PACKAGE);
        Set<Class<?>> annotated = reflections
                .getTypesAnnotatedWith(Module.class);
        annotated.forEach(e -> {
            String nameKey = e.getAnnotation(Module.class).value();

            for (Type t : e.getInterfaces()) {
                String type = t.getTypeName();
                if (type.equals(Stringifyer.class.getName())) {
                    Class<? extends Stringifyer> stringifyer =
                            (Class<? extends Stringifyer>) e;
                    stringifyerMapBinder.addBinding(nameKey).to(stringifyer);
                } else if (type.equals(Builder.class.getName())) {
                    Class<? extends Builder> builder =
                            (Class<? extends Builder>) e;
                    builderMapBinder.addBinding(nameKey).to(builder);
                } else {
                    logger.debug("No fitting type found. Type was: {}",
                            type);
                }
            }
        });
    }
}
