package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;
import ch.hsr.adv.lib.stack.logic.domain.StackElement;

import java.util.Stack;


/**
 * Builder Implementation for stack module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input stack.
 *
 * @param <T> type of the stack content
 */
@Module("stack")
class StackBuilder<T> implements Builder {

    private final Session session = new Session();
    private StackModule<T> module;
    private Snapshot snapshot;

    /**
     * Builds a session with a snapshot of the current state of the stack
     *
     * @param advModule           containing the snapshot data
     * @param snapshotDescription a helpful explanation for the snapshot
     * @return a session containing the snapshot data
     */
    @Override
    public Session build(ADVModule advModule, String snapshotDescription) {
        this.module = (StackModule<T>) advModule;
        session.setNames(advModule.getModuleName(), advModule.getSessionName());

        initSnapshot(snapshotDescription);
        buildElements();
        return session;
    }

    private void initSnapshot(String snapshotDescription) {
        snapshot = new Snapshot();
        snapshot.setSnapshotDescription(snapshotDescription);
        session.setSnapshot(snapshot);
    }

    private void buildElements() {
        Stack<T> clonedStack = new Stack<>();
        ADVStack<T> originalStack = module.getStack();
        int size = originalStack.size();
        for (int i = 0; i < size; i++) {
            T element = originalStack.pop();
            clonedStack.push(element);

            StackElement stackElement = new StackElement();
            stackElement.setId(i);
            stackElement.setContent(element.toString());

            ADVStyle style = module.getStyleMap().get(i);
            stackElement.setStyle(style);
            snapshot.addElement(stackElement);
        }

        for (int i = 0; i < size; i++) {
            originalStack.push(clonedStack.pop());
        }
    }

}
