package ch.adv.lib.core.logic.mocks;

import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.logic.Stringifyer;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.adv.lib.core.logic.domain.styles.presets.ADVDefaultStyle;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

public class ADVTestModule implements ADVModule {
    @Inject
    private Builder builder;

    @Inject
    private Stringifyer stringifyer;

    @Override
    public Map<Integer, ADVStyle> getStyleMap() {
        Map<Integer, ADVStyle> map = new HashMap<>();
        map.put(1, new ADVDefaultStyle());
        return map;
    }

    @Override
    public String getSessionName() {
        return "testSession";
    }

    @Override
    public String getModuleName() {
        return "test";
    }

    @Override
    public Stringifyer getStringifyer() {
        return stringifyer;
    }

    @Override
    public Builder getBuilder() {
        return builder;
    }
}
