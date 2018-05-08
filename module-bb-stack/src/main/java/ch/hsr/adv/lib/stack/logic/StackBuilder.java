package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;
import ch.hsr.adv.lib.stack.logic.domain.ModuleConstants;
import ch.hsr.adv.lib.stack.logic.domain.StackElement;
import com.google.inject.Singleton;

import java.util.Stack;


/**
 * Builder Implementation for stack module. It builds a whole session with a
 * snapshot and fitting ADVElements from the input stack.
 *
 * @param <T> type of the stack content
 */
@Singleton
@Module(ModuleConstants.MODULE_NAME)
class StackBuilder<T> implements Builder {

    /**
     * Builds a session with a snapshot of the current state of the stack
     *
     * @param module containing the snapshot data
     * @return a session containing the snapshot data
     */
    @Override
    public ModuleGroup build(ADVModule module) {
        StackModule<T> stackModule = (StackModule<T>) module;
        ModuleGroup moduleGroup = new ModuleGroup(stackModule.getModuleName());

        Stack<T> clonedStack = new Stack<>();
        ADVStack<T> originalStack = stackModule.getStack();
        int size = originalStack.size();
        for (int i = 0; i < size; i++) {
            T element = originalStack.pop();
            clonedStack.push(element);

            StackElement stackElement = new StackElement();
            stackElement.setId(i);
            stackElement.setContent(element.toString());

            ADVStyle style = stackModule.getStyleMap().get(i);
            stackElement.setStyle(style);
            moduleGroup.addElement(stackElement);
        }

        for (int i = 0; i < size; i++) {
            originalStack.push(clonedStack.pop());
        }

        return moduleGroup;
    }
}
