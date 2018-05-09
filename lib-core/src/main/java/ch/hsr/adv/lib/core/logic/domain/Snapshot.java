package ch.hsr.adv.lib.core.logic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the state of a data structure in the user's module
 * implementation. A snapshot always belongs to a session. It is sent to the
 * ADVCore UI to be displayed.
 */
public class Snapshot {
    private static final transient AtomicInteger SNAPSHOT_COUNTER = new
            AtomicInteger(0);
    private final long snapshotId;
    private String snapshotDescription;
    private List<ModuleGroup> moduleGroups = new ArrayList<>();


    public Snapshot() {
        snapshotId = SNAPSHOT_COUNTER.incrementAndGet();
    }

    public long getSnapshotId() {
        return snapshotId;
    }

    public String getSnapshotDescription() {
        return snapshotDescription;
    }

    public void setSnapshotDescription(String snapshotDescription) {
        this.snapshotDescription = snapshotDescription;
    }

    public List<ModuleGroup> getModuleGroups() {
        return moduleGroups;
    }

    public void setModuleGroups(List<ModuleGroup> moduleGroups) {
        this.moduleGroups = moduleGroups;
    }
}
