package ch.adv.lib.array;

import ch.adv.lib.model.*;

import javax.inject.Inject;
import java.util.List;

public class ArrayBuilder<T> implements Builder {
    private ArrayModule<T> module;
    private Snapshot snapshot;

    private final Session session;

    @Inject
    public ArrayBuilder(Session session) {
        this.session = session;
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
        for (T t : array) {
            ArrayElement e = new ArrayElement();
            if (t != null){
                e.setContent(t.toString());
            }
            //TODO: change id creation
            e.setId(e.hashCode());
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
