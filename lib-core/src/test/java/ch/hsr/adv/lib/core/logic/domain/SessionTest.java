package ch.hsr.adv.lib.core.logic.domain;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class SessionTest {

    @Inject
    private Session sut;

    @Test
    public void overrideSnapshotTest() {
        // GIVEN
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