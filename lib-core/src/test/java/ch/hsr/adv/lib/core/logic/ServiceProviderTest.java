package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.logic.mocks.TestConstants;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JukitoRunner.class)
public class ServiceProviderTest {

    private static final String WRONG_KEY = "key";
    private ServiceProvider sut;
    private Map<String, Builder> builderMap;
    private Map<String, Stringifyer> stringifyerMap;
    @Inject
    private DefaultStringifyer defaultStringifyer;

    @Before
    public void setUp(Builder testBuilder, Stringifyer testStringifyer)
            throws Exception {
        builderMap = new HashMap<>();
        builderMap.put(TestConstants.MODULE_NAME, testBuilder);
        stringifyerMap = new HashMap<>();
        stringifyerMap.put(TestConstants.MODULE_NAME, testStringifyer);

        sut = new ServiceProvider(builderMap, stringifyerMap,
                defaultStringifyer);
    }

    @Test
    public void getBuilderGoodTest() {
        // WHEN
        Builder actual = sut.getBuilder(TestConstants.MODULE_NAME);

        // THEN
        assertEquals(builderMap.get(TestConstants.MODULE_NAME), actual);
    }

    @Test
    public void getBuilderBadTest() {
        // WHEN
        Builder actual = sut.getBuilder(WRONG_KEY);

        // THEN
        assertNull(actual);
    }

    @Test
    public void getStringifyerGoodTest() {
        // WHEN
        Stringifyer actual = sut.getStringifyer(TestConstants.MODULE_NAME);

        // THEN
        assertEquals(stringifyerMap.get(TestConstants.MODULE_NAME), actual);
    }

    @Test
    public void getStringifyerBadTest() {
        // WHEN
        Stringifyer actual = sut.getStringifyer(WRONG_KEY);

        // THEN
        assertEquals(defaultStringifyer,actual);
    }

}