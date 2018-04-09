package ch.adv.lib.core.mocks;

import ch.adv.lib.core.app.ADVModule;
import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.service.Stringifyer;
import ch.adv.lib.core.domain.styles.ADVStyle;
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
        map.put(1, new ADVStyle() {
            @Override
            public int getFillColor() {
                return 0;
            }

            @Override
            public int getStrokeColor() {
                return 0;
            }

            @Override
            public String getStrokeStyle() {
                return null;
            }

            @Override
            public int getStrokeThickness() {
                return 0;
            }
        });
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
