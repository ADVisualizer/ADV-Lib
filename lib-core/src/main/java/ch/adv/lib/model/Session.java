package ch.adv.lib.model;

import java.util.Calendar;

public class Session {
    private String module;
    private String sessionName;

    private static final long sessionId = Calendar.getInstance().getTimeInMillis();
    private final Snapshot[] snapshots;

    public Session() {
        snapshots = new Snapshot[1];
    }

    /**
     * Sets the module name, which is uniquely defined for each module.
     * Sets an arbitrary sessionName, which can be freely chosen by the user.
     *
     * @param moduleName  the keyword identifying the module
     * @param sessionName a session name to be displayed in the ui
     */
    public void setNames(String moduleName, String sessionName) {
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

    /**
     * The module name is used as a keyword. Has to be identical in the ADV Lib and ADV UI.
     *
     * @return the module name
     */
    public String getModule() {
        return module;
    }

    /**
     * @return a unique sessionId, which has to stay consistent over different snapshots of the same session
     */
    public long getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

}
