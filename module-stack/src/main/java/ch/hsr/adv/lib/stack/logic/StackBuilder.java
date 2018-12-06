package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.stack.logic.ConstantsStack;
import ch.hsr.adv.commons.stack.logic.domain.StackElement;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;
import com.google.inject.Singleton;

import java.util.Stack;


/**
 * Builder Strategy for stack module. It builds a ModuleGroup containing
 * the module data.
 * <p>
 * Can only handle stack module!
 */
@Singleton
@Module(ConstantsStack.MODULE_NAME)
class StackBuilder implements Builder {

    @Override
    public ModuleGroup build(ADVModule module) {
        return createModuleGroup(module);
    }

    private <T> ModuleGroup createModuleGroup(ADVModule module) {
        StackModule stackModule = (StackModule) module;
        ModuleGroup moduleGroup = new ModuleGroup(stackModule.getModuleName(),
                stackModule.getPosition());

        Stack<T> clonedStack = new Stack<>();
        @SuppressWarnings("unchecked")
        ADVStack<T> originalStack = (ADVStack<T>) stackModule.getStack();
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
