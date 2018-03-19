package ch.adv.lib.model;

import javax.inject.Singleton;
import java.util.Calendar;

@Singleton
public class Session {
    private String module;
    private String sessionName;


    private final long sessionId;
    private final Snapshot[] snapshots;

    public Session() {
        Calendar cal = Calendar.getInstance();
        this.sessionId = cal.getTimeInMillis();
        snapshots = new Snapshot[1];
    }

    public void setNames(String moduleName, String sessionName){
        this.module = moduleName;
        this.sessionName = sessionName;

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
