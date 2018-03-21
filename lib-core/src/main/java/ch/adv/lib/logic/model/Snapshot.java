package ch.adv.lib.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the state of a data structure in the user's module
 * implementation. A snapshot always belongs to a session. It is sent to the
 * ADV UI to be displayed.
 */
public class Snapshot {
    private final long snapshotId;
    private String snapshotDescription;
    private List<ADVElement> elements;
    private List<ADVRelation> relations;
    private static final transient AtomicInteger SNAPSHOT_COUNTER = new
            AtomicInteger(0);

    public Snapshot() {
        snapshotId = SNAPSHOT_COUNTER.incrementAndGet();
        elements = new ArrayList<>();
        relations = new ArrayList<>();
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

    public void addElement(ADVElement<?> element) {
        elements.add(element);
    }

    public void addRelation(ADVRelation relation) {
        relations.add(relation);
    }

    public List<ADVElement> getElements() {
        return elements;
    }

    public List<ADVRelation> getRelations() {
        return relations;
    }
}
