package ch.adv.lib;

import ch.adv.lib.logic.Builder;
import ch.adv.lib.logic.Stringifyer;
import ch.adv.lib.logic.model.styles.ADVStyle;

import java.util.Map;

public interface ADVModule {
    Map<Integer, ADVStyle> getStyleMap();
    String getSessionName();

    String getModuleName();

    Stringifyer getStringifyer();

    Builder getBuilder();
}
