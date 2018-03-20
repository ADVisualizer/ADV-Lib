package ch.adv.lib.mocks;

import ch.adv.lib.access.Stringifyer;
import ch.adv.lib.model.ADVModule;
import ch.adv.lib.model.ADVStyle;
import ch.adv.lib.model.Builder;
import com.google.inject.Inject;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

public final class ADVTestModule implements ADVModule{
    @Inject
    private Builder builder;

    @Inject
    private Stringifyer stringifyer;

    @Override
    public Map<Integer, ADVStyle> getStyleMap() {
        Map<Integer, ADVStyle> map = new HashMap<>();
        map.put(1, new ADVStyle() {
            @Override
            public String getFillColor() {
                return null;
            }

            @Override
            public String getStrokeColor() {
                return null;
            }

            @Override
            public String getStrokeStyle() {
                return null;
            }

            @Override
            public String getStrokeThickness() {
                return null;
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
