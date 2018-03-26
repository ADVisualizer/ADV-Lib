package ch.adv.lib;

import ch.adv.lib.mocks.ADVTestModule;
import ch.adv.lib.mocks.MockConnector;
import ch.adv.lib.service.Connector;
import ch.adv.lib.util.ADVConnectionException;
import ch.adv.lib.util.ADVException;
import com.google.inject.Inject;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.jukito.TestScope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//http://www.vogella.com/tutorials/Mockito/article.html
//https://blog.arcbees.com/2015/11/04/testing-gwtp-applications-with-jukito
// -introduction-to-jukito/
@RunWith(JukitoRunner.class)
public class ADVTest {
    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
            bind(Connector.class).to(MockConnector.class).in(TestScope
                    .SINGLETON);
            bindSpy(MockConnector.class);
        }
    }

    @Inject
    private ADVTestModule module;

    @Inject
    private ADV adv;

    @Inject
    private Connector connector;

    @Test(expected = ADVConnectionException.class)
    public void noConnectionTest() throws ADVException {
        ADV.launch(new String[0]);
    }

    @Test
    public void handSnapshotStringToConnector() {
        adv.snapshot(module, "test");
        verify(connector, Mockito.times(1)).send(any());
    }

    @Test
    public void callConnectorOnDisconnect() {
        adv.disconnect();
        verify(connector, Mockito.times(1)).disconnect();
    }

}