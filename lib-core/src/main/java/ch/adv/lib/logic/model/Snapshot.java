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
    private static final transient AtomicInteger SNAPSHOT_COUNTER = new
            AtomicInteger(0);
    private final long snapshotId;
    private String snapshotDescription;
    private List<ADVElement> elements;
    private List<ADVRelation> relations;

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

    /**
     * Adds an element to the snapshot. Every module offers one or more
     * specific elements, which describe the content of the snapshot. An
     * element could for example be a node in a graph.
     *
     * @param element to be added
     */
    public void addElement(ADVElement<?> element) {
        elements.add(element);
    }

    /**
     * Adds a relation between two {@link ADVElement}s. A relation can show an
     * ordering between elements as in elements of an array or an actual
     * connection like an edge in a graph.
     *
     * @param relation between two elements
     */
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
