package ch.adv.lib.array;

import ch.adv.lib.array.domain.ArrayElement;
import ch.adv.lib.array.domain.ArrayRelation;
import ch.adv.lib.array.domain.Coordinates;
import ch.adv.lib.core.app.ADVModule;
import ch.adv.lib.core.domain.styles.ADVStyle;
import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.core.domain.ADVElement;
import ch.adv.lib.core.domain.Session;
import ch.adv.lib.core.domain.Snapshot;

import java.util.List;

/**
 * Builder Implementation for array module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input array.
 * Class must be stateless!
 *
 * @param <T> the type of content of the array
 */
class ArrayBuilder<T> implements Builder {
    private ArrayModule<T> module;
    private Snapshot snapshot;

    private final Session session;

    ArrayBuilder() {
        this.session = new Session();
    }


    /**
     * Builds a session with a snapshot of the array contained in the array
     * module.
     *
     * @param advModule           containing the snapshot data
     * @param snapshotDescription a helpful explanation for the snapshot
     * @return a session containing the snapshot data
     */
    @Override
    public Session build(ADVModule advModule, String snapshotDescription) {
        this.module = (ArrayModule<T>) advModule;
        session.setNames(advModule.getModuleName(), advModule.getSessionName());
        initSnapshot(snapshotDescription);
        buildElements();
        buildRelations();
        return session;
    }

    @Override
    public Session build(ADVModule advModule) {
        return build(advModule, null);
    }

    private void initSnapshot(String snapshotDescription) {
        this.snapshot = new Snapshot();
        session.setSnapshot(snapshot);
        snapshot.setSnapshotDescription(snapshotDescription);
    }


    private void buildElements() {
        T[] array = module.getArray();
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            ArrayElement e = new ArrayElement();
            e.setId(i);
            if (t != null) {
                e.setContent(t.toString());
            }
            ADVStyle style = module.getStyleMap().get(i);
            e.setStyle(style);
            Coordinates cords = module.getCoordinates().get(i);
            if (cords != null) {
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
