package ch.adv.lib.model;

import ch.adv.lib.model.Snapshot;

import java.util.Calendar;

public class Session {
    private final String module;
    private final long sessionId;
    private final String sessionName;
    private final Snapshot[] snapshots;

    public Session(String moduleName, String sessionName) {
        Calendar cal = Calendar.getInstance();
        this.sessionId = cal.getTimeInMillis();
        this.module = moduleName;
        this.sessionName = sessionName;
        snapshots = new Snapshot[1];
    }

    /**
     * Sets the current snapshot to be sent to the UI. Replaces previous snapshot.
     *
     * @param snapshot the current snapshot
     */
    public void setSnapshot(Snapshot snapshot) {
        snapshots[0] = snapshot;
    }

    public Snapshot getSnapshot() {
        return snapshots[0];
    }

    public String getModule() {
        return module;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

}
