package ch.adv.lib.model;

import ch.adv.lib.access.Stringifyer;

import java.util.HashMap;
import java.util.Map;

public interface ADVModule {
    Map<Integer, ADVStyle> getStyleMap();
    String getSessionName();

    String getModuleName();

    Stringifyer getStringifyer();

    Builder getBuilder();
}
