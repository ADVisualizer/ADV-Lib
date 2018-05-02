package ch.adv.lib.stack.logic;

import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.domain.Module;
import ch.adv.lib.core.logic.domain.Session;
import ch.adv.lib.core.logic.domain.Snapshot;
import ch.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.adv.lib.core.logic.Builder;
import ch.adv.lib.stack.logic.domain.Coordinate;
import ch.adv.lib.stack.logic.domain.StackElement;


/**
 * Builder Implementation for stack module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input stack.
 * Class must be stateless!
 *
 * @param <T> the type of content of the stack
 */
@Module("stack")
class StackBuilder<T> implements Builder {

    private static final String SHOW_OBJECT_RELATIONS = "SHOW_OBJECT_RELATIONS";

    private final Session session = new Session();
    private ch.adv.lib.stack.logic.StackModule<T> module;
    private Snapshot snapshot;

    /**
     * Builds a session with a snapshot of the stack contained in the stack
     * module.
     *
     * @param advModule           containing the snapshot data
     * @param snapshotDescription a helpful explanation for the snapshot
     * @return a session containing the snapshot data
     */
    @Override
    public Session build(ADVModule advModule, String snapshotDescription) {
        this.module = (ch.adv.lib.stack.logic.StackModule<T>) advModule;

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
        T[] stack = module.getStack();
        for (int i = 0; i < stack.length; i++) {
            T t = stack[i];
            StackElement e = new StackElement();
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
