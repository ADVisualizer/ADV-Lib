package ch.adv.lib;

import ch.adv.lib.access.SocketConnector;
import com.google.inject.Inject;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.jukito.TestScope;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


//TODO: probably not very clever tests. more a proof of concept
@RunWith(JukitoRunner.class)
public class ADVTest {
    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
            bindSpy(SocketConnector.class, new SocketConnector()).in(TestScope.SINGLETON);
        }
    }

    @Inject
    private ADV adv;

    @Inject
    private SocketConnector connector;

    @Test
    public void testTest() {
        assertTrue(1 + 1 == 2);
    }
/*
    @Test(expected = ADVConnectionException.class)
    public void noConnectionTest() throws ADVException {
        ADV.launch(new String[0]);
    }

    @Test
    public void handSnapshotStringToConnector() {
        ADVModule m = new ADVTestModule();
        String secondCall = m.getStyleMap().get(1).toString();
        Mockito.doReturn(true).when(connector).send(secondCall);
        adv.snapshot(m, "test");
        verify(connector, Mockito.times(1)).send(any());
    }

    @Test
    public void callConnectorOnDisconnect(){
        Mockito.doReturn(true).when(connector).disconnect();
        adv.disconnect();
        verify(connector, Mockito.times(1)).disconnect();
    }

*/
}