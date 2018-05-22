package ch.hsr.adv.lib.core.logic.domain;


import ch.hsr.adv.commons.core.logic.domain.Snapshot;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionTest {
    @Test
    public void buildSessionTest() {
        // GIVEN
        Session sut = new Session("test");
        Snapshot snapshot = new Snapshot();
        assertEquals(sut.getSnapshot(), null);

        // WHEN
        sut.setSnapshot(snapshot);

        // THEN
        assertEquals(snapshot, sut.getSnapshot());
        assertTrue(0 != sut.getSessionId());
        assertTrue(0 < sut.getSessionId());
        assertTrue(Instant.now().toEpochMilli() >= sut.getSessionId());
    }

    @Test
    public void overrideSnapshotTest() {
        // GIVEN
        Session sut = new Session("test");
        Snapshot snapshot1 = new Snapshot();
        Snapshot snapshot2 = new Snapshot();
        sut.setSnapshot(snapshot1);

        // WHEN
        sut.setSnapshot(snapshot2);

        // THEN
        assertEquals(snapshot2, sut.getSnapshot());
    }
}