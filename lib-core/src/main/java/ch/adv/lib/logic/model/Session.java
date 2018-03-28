package ch.adv.lib.logic.model;

import java.time.Instant;

/**
 * A Session encapsulates the current snapshot of a user's module
 * implementation. All snapshots during the code's lifespan belong to the
 * same session, which needs a unique id.
 */
public class Session {
    private String moduleName;
    private String sessionName;
    private final Snapshot[] snapshots;
    private static final long SESSION_ID = Instant.now().toEpochMilli();

    public Session() {
        this.snapshots = new Snapshot[1];
    }

    /**
     * Sets the module name, which is uniquely defined for each module.
     * Sets an arbitrary sessionName, which can be freely chosen by the user.
     *
     * @param advModuleName  the keyword identifying the module
     * @param advSessionName a session name to be displayed in the ui
     */
    public void setNames(String advModuleName, String advSessionName) {
        this.moduleName = advModuleName;
        this.sessionName = advSessionName;
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
     * The module name is used as a keyword. Has to be identical in the ADV
     * Lib and ADV UI.
     *
     * @return the module name
     */
    public String getModuleName() {
        return moduleName;
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
