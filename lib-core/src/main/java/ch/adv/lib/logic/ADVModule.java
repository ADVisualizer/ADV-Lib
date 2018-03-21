package ch.adv.lib.logic;

import ch.adv.lib.logic.model.ADVStyle;
import ch.adv.lib.service.Stringifyer;

import java.util.Map;

public interface ADVModule {
    Map<Integer, ADVStyle> getStyleMap();
    String getSessionName();

    String getModuleName();

    Stringifyer getStringifyer();

    Builder getBuilder();
}
