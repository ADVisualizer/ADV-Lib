package ch.hsr.adv.lib.core.access;

import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;

import java.lang.reflect.Modifier;

/**
 * Provides preconfigured GsonBuilder
 */
@Singleton
public class GsonProvider implements JsonBuilderProvider<GsonBuilder> {

    private final GsonBuilder minifier;
    private final GsonBuilder prettifyer;

    public GsonProvider() {
        this.prettifyer = new GsonBuilder();
        this.prettifyer.setPrettyPrinting();
        this.prettifyer.excludeFieldsWithModifiers(Modifier.TRANSIENT);

        // transient and static fields are excluded by default. we only want
        // transient fields to be excluded. That's why we exclude them
        // explicitly!
        this.minifier = new GsonBuilder();
        this.minifier.excludeFieldsWithModifiers(Modifier.TRANSIENT);
    }

    /**
     * Get a pretty-printing json serializer to write to the data store.
     *
     * @return a pretty-printing json serializer
     */
    public GsonBuilder getMinifier() {
        return minifier;
    }

    /**
     * Get a pretty-printing json serializer to create a json representation
     * of an object.
     *
     * @return a pretty-printing json serializer
     */
    public GsonBuilder getPrettifyer() {
        return prettifyer;
    }
}

