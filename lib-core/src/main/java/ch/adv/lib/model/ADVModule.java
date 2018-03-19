package ch.adv.lib.model;

import ch.adv.lib.access.Stringifyer;

import java.util.HashMap;
import java.util.Map;

public interface ADVModule {
    default Map<Integer, ADVStyle> getStyleMap() {
        return new HashMap<>();
    }

    String getSessionName();

    String getModuleName();

    Stringifyer getStringifyer();

    Builder getBuilder();
}
