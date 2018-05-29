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

    @Inject
    private SocketConnector sut;
    
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

}