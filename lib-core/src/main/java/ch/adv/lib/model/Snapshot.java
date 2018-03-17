package ch.adv.lib.model;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private final long snapshotId;
    private String snapshotDescription;
    private List<ADVElement<?>> elements;
    private List<ADVRelation> relations;
    private static transient long snapshotCounter;

    public Snapshot(){
        snapshotId = ++snapshotCounter;
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

    public void addElement(ADVElement<?> element){
        elements.add(element);
    }

    public void addRelation(ADVRelation relation){
        relations.add(relation);
    }

    public List<ADVElement<?>> getElements(){
        return elements;
    }

    public List<ADVRelation> getRelations(){
        return relations;
    }
}
