package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.commons.core.logic.domain.Session;
import ch.hsr.adv.commons.core.logic.domain.Snapshot;
import com.google.inject.Singleton;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Component which is responsible for building the session with one snapshot
 */
@Singleton
public class CoreBuilder {
    private static final long SESSION_ID = Instant.now().toEpochMilli();
    private static final AtomicInteger SNAPSHOT_COUNTER = new
            AtomicInteger(0);

    /**
     * Builds a single session with a single snapshot
     *
     * @param advModule           module
     * @param snapshotDescription snapshot description
     * @return session
     */
    public Session build(ADVModule advModule, String snapshotDescription) {
        Session session = new Session();
        session.setSessionName(advModule.getSessionName());
        session.setSessionId(SESSION_ID);
        Snapshot snapshot = new Snapshot(SNAPSHOT_COUNTER.incrementAndGet());
        snapshot.setSnapshotDescription(snapshotDescription);
        session.getSnapshots().add(snapshot);
        return session;
    }
}
