package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.lib.core.access.Connector;
import ch.hsr.adv.lib.core.access.GsonProvider;
import ch.hsr.adv.lib.core.access.JsonBuilderProvider;
import ch.hsr.adv.lib.core.logic.mocks.TestADVModule;
import ch.hsr.adv.lib.core.logic.mocks.TestConstants;
import ch.hsr.adv.lib.core.logic.util.ADVException;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(JukitoRunner.class)
public class ADVCoreTest {

    @Inject
    private ADVCore sut;

    @Inject
    private TestADVModule testModule;

    @Inject
    private Connector connector;


    @Before
    public void setUp(Builder builder, Stringifyer stringifyer,
                      ServiceProvider provider) throws Exception {
        doReturn(builder).when(provider).getBuilder(any());
        doReturn(stringifyer).when(provider).getStringifyer(any());
        doReturn(true).when(connector).connect();
    }

    @Test
    public void snapshotE2ETest() {
        // WHEN
        sut.snapshot(testModule, TestConstants.SNAPSHOT_DESC);

        // THEN
        Mockito.verify(connector).send(any());
    }

    @Test
    public void setupE2ETest() throws ADVException {
        // GIVEN
        String[] args = new String[2];
        args[0] = "--port=1111";
        args[1] = "--host=1.1.1.1";

        // WHEN
        sut.setup(args);

        // THEN
        Mockito.verify(connector).setPort(1111);
        Mockito.verify(connector).setHost("1.1.1.1");
    }

    @Test
    public void disconnectE2ETest(){
        // WHEN
        sut.disconnectFromSocket();

        // THEN
        Mockito.verify(connector).disconnect();
    }


    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
            forceMock(ServiceProvider.class);
            bind(new TypeLiteral<JsonBuilderProvider<GsonBuilder>>() {
            }).to(new TypeLiteral<GsonProvider>() {
            });
        }
    }
}