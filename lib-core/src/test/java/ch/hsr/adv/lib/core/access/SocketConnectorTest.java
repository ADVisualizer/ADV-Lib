package ch.hsr.adv.lib.core.access;

import com.google.inject.Inject;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(JukitoRunner.class)
public class SocketConnectorTest {

    private static final String JSON = "{\n"
            + "  \"sessionId\": 1526280311996,\n"
            + "  \"sessionName\": \"testSession\"\n"
            + "}";
    private static final String FINAL_JSON = "{\"command\":\"TRANSMIT\","
            + "\"json\":\"{\\n  \\\"sessionId\\\": 1526280311996,\\n  "
            + "\\\"sessionName\\\": \\\"testSession\\\"\\n}\"}\n";

    private static final String RESPONSE = "{\"command\":\"ACK\"}";

    @Inject
    private SocketConnector sut;
    private ByteArrayOutputStream output;
    private ByteArrayInputStream input;


    @Before
    public void setUp(Socket socket) throws Exception {
        output = new ByteArrayOutputStream();
        byte[] response = RESPONSE.getBytes("UTF-8");
        input = new ByteArrayInputStream(response);
        when(socket.getOutputStream()).thenReturn(output);
        when(socket.getInputStream()).thenReturn(input);

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
    public void sendTest() throws UnsupportedEncodingException {
        // WHEN
        boolean success = sut.send(JSON);

        // THEN
        assertTrue(success);
        assertEquals(
                FINAL_JSON,
                output.toString("UTF-8"));
    }

    public static class Module extends JukitoModule {

        @Override
        protected void configureTest() {
            forceMock(Socket.class);
        }
    }
}