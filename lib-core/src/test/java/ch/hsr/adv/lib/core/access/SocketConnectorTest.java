package ch.hsr.adv.lib.core.access;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class SocketConnectorTest {

    private static final String JSON = "{\n"
            + "  \"sessionId\": 1526280311996,\n"
            + "  \"sessionName\": \"testSession\"\n"
            + "}";

    @Inject
    private SocketConnector sut;


    @Before
    public void setUp() throws Exception {
        sut.connect();
    }

    @After
    public void tearDown() throws Exception {
        sut.disconnect();
    }

    @Test
    public void setPortGoodTest() {
        // WHEN
        boolean success = sut.setPort(1024);

        // THEN
        assertTrue(success);
    }

    @Test
    public void setPortBadTest() {
        // WHEN
        boolean success = sut.setPort(80);

        // THEN
        assertFalse(success);
    }

    @Test
    public void setHostGoodTest() {
        // WHEN
        boolean success = sut.setHost("1.1.1.1");

        // THEN
        assertTrue(success);
    }

    @Test
    public void setHostBadTest() {
        // WHEN
        boolean success = sut.setHost(null);

        // THEN
        assertFalse(success);
    }

    @Test
    public void sendTest() {
        // WHEN
        boolean success = sut.send(JSON);

        // THEN
        assertTrue(success);
    }
}