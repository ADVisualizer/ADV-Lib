package ch.adv.lib.core.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Contains preconfigured GsonBuilder
 */
public class GsonProvider {
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

    public Gson getMinifier() {
        return minifier.create();
    }

    public Gson getPrettifyer() {
        return prettifyer.create();
    }
}
