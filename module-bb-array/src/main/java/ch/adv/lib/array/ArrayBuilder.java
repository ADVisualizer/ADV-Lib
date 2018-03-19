package ch.adv.lib.array;

import ch.adv.lib.model.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBuilder<T> implements Builder {
    private ArrayModule<T> module;
    private Snapshot snapshot;

    private final Session session;

    public ArrayBuilder() {
        this.session = new Session();
    }

    @Override
    public Session build(ADVModule module, String snapshotDescription) {
        this.module = (ArrayModule<T>) module;
        session.setNames(module.getModuleName(), module.getSessionName());
        initSnapshot(snapshotDescription);
        buildElements();
        buildRelations();
        return session;
    }

    private void initSnapshot(String snapshotDescription) {
        this.snapshot = new Snapshot();
        session.setSnapshot(snapshot);
        snapshot.setSnapshotDescription(snapshotDescription);
    }


    private void buildElements() {
        T[] array = module.getArray();
        for (int i=0; i< array.length; i++){
            T t = array[i];
            ArrayElement e = new ArrayElement();
            e.setId(i);
            if (t != null) {
                e.setContent(t.toString());
            }
            e.setStyle(module.getStyleMap().get(i));
            Coordinates cords = module.getCoordinates().get(i);
            if (cords != null){
                e.setFixedPosX(cords.getX());
                e.setFixedPosY(cords.getY());
            }
            snapshot.addElement(e);
        }
    }

    private void buildRelations() {
        List<ADVElement> elements = snapshot.getElements();
        for (int i = 0; i < elements.size() - 1; i++) {
            ADVElement from = elements.get(i);
            ADVElement to = elements.get(i + 1);
            ArrayRelation rel = new ArrayRelation();
            rel.setSourceElementId(from.getElementId());
            rel.setTargetElementId(to.getElementId());
            snapshot.addRelation(rel);
        }
    }
}
