package ch.adv.lib.logic.model;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class SessionTest {

    @Inject
    private Session session;

    @Inject
    private Snapshot snapshot1;

    @Inject
    private Snapshot snapshot2;

    @Test
    public void overrideSnapshotTest() {
        assertEquals(session.getSnapshot(), null);
        session.setSnapshot(snapshot1);
        assertEquals(snapshot1, session.getSnapshot());
        session.setSnapshot(snapshot2);
        assertEquals(snapshot2, session.getSnapshot());
    }
}