package ch.adv.lib.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class GsonProvider {
    private final GsonBuilder minifier;
    private final GsonBuilder prettifyer;

    public GsonProvider() {
        this.prettifyer = new GsonBuilder();
        this.prettifyer.setPrettyPrinting();

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
