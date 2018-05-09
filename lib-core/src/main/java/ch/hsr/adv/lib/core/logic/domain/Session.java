package ch.hsr.adv.lib.core.logic.domain;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

/**
 * A Session encapsulates the current snapshot of a user's module
 * implementation. All snapshots during the code's lifespan belong to the
 * same session, which needs a unique id.
 */
public class Session {

    @SerializedName("sessionId")
    private static final long SESSION_ID = Instant.now().toEpochMilli();
    private final String sessionName;
    private final Snapshot[] snapshots = new Snapshot[1];

    public Session(String sessionName) {
        this.sessionName = sessionName;
    }

    /**
     * Sets the current snapshot to be sent to the UI. Replaces previous
     * snapshot.
     *
     * @param snapshot the current snapshot
     */
    public void setSnapshot(Snapshot snapshot) {
        snapshots[0] = snapshot;
    }

    public Snapshot getSnapshot() {
        return snapshots[0];
    }

    /**
     * @return a unique sessionId, which has to stay consistent over
     * different snapshots of the same session
     */
    public long getSessionId() {
        return SESSION_ID;
    }

    public String getSessionName() {
        return sessionName;
    }
}
