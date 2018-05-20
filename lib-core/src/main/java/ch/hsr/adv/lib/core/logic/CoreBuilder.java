package ch.hsr.adv.lib.core.logic;

import ch.hsr.adv.commons.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.core.logic.domain.Session;
import com.google.inject.Singleton;

/**
 * Component which is responsible for building the session with one snapshot
 */
@Singleton
public class CoreBuilder {

    /**
     * Builds a single session with a single snapshot
     *
     * @param advModule           module
     * @param snapshotDescription snapshot description
     * @return session
     */
    public Session build(ADVModule advModule, String snapshotDescription) {
        Session session = new Session(advModule.getSessionName());
        Snapshot snapshot = new Snapshot();
        snapshot.setSnapshotDescription(snapshotDescription);
        session.setSnapshot(snapshot);
        return session;
    }
}
