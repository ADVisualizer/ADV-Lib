package ch.adv.lib.array.logic;

import ch.adv.lib.array.domain.ArrayElement;
import ch.adv.lib.array.domain.Coordinate;
import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.domain.Session;
import ch.adv.lib.core.logic.domain.Snapshot;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.adv.lib.core.logic.Builder;


/**
 * Builder Implementation for array module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input array.
 * Class must be stateless!
 *
 * @param <T> the type of content of the array
 */
class ArrayBuilder<T> implements Builder {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    private final Session session = new Session();
    private ArrayModule<T> module;
    private Snapshot snapshot;

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
        if (module.showObjectRelations()) {
            session.addFlag(SHOW_OBJECT_RELATIONS);
        }

        initSnapshot(snapshotDescription);
        buildElements();
        buildRelations();
        return session;
    }

    private void initSnapshot(String snapshotDescription) {
        snapshot = new Snapshot();
        snapshot.setSnapshotDescription(snapshotDescription);
        session.setSnapshot(snapshot);
    }

    private void buildElements() {
        T[] array = module.getArray();
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            ArrayElement e = new ArrayElement();
            e.setId(i);

            if (t != null) {
                e.setContent(t.toString());
            } else {
                if (!module.showObjectRelations()) {
                    e.setContent("null");
                }
            }

            ADVStyle style = module.getStyleMap().get(i);
            e.setStyle(style);
            Coordinate cords = module.getCoordinates().get(i);
            if (cords != null) {
                e.setFixedPosX(cords.getX());
                e.setFixedPosY(cords.getY());
            }
            snapshot.addElement(e);
        }
    }

    private void buildRelations() {
        module.getRelations().forEach(r -> {
            snapshot.addRelation(r);
        });
    }

}