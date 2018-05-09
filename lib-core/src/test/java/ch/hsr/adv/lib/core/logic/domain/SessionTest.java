package ch.hsr.adv.lib.core.logic.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionTest {

    @Test
    public void overrideSnapshotTest() {
        // GIVEN
        Session sut = new Session("test");
        Snapshot snapshot1 = new Snapshot();
        Snapshot snapshot2 = new Snapshot();
        assertEquals(sut.getSnapshot(), null);
        sut.setSnapshot(snapshot1);
        assertEquals(snapshot1, sut.getSnapshot());

        // WHEN
        sut.setSnapshot(snapshot2);

        // THEN
        assertEquals(snapshot2, sut.getSnapshot());
    }
}