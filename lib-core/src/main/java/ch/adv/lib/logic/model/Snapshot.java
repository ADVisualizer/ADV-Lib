package ch.adv.lib.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Snapshot {
    private final long snapshotId;
    private String snapshotDescription;
    private List<ADVElement> elements;
    private List<ADVRelation> relations;
    private static transient final AtomicInteger snapshotCounter = new AtomicInteger(0);

    public Snapshot() {
        snapshotId = snapshotCounter.incrementAndGet();
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
