package ch.adv.lib.core.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Contains preconfigured GsonBuilder
 */
public class GsonProvider implements JsonBuilderProvider<Gson> {
    private final GsonBuilder minifier;
    private final GsonBuilder prettifyer;

    public GsonProvider() {
        this.prettifyer = new GsonBuilder();
        this.prettifyer.setPrettyPrinting();
        this.prettifyer.excludeFieldsWithModifiers(Modifier.TRANSIENT);

        this.minifier = new GsonBuilder();
        // transient and static fields are excluded by default. we only want
        // transient fields to be excluded. That's why we exclude them
        // explicitly!
        this.minifier.excludeFieldsWithModifiers(Modifier.TRANSIENT);
    }

    @Override
    public Gson getMinifier() {
        return minifier.create();
    }

    @Override
    public Gson getPrettifyer() {
        return prettifyer.create();
    }
}
