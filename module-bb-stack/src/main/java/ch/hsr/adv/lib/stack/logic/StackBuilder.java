package ch.hsr.adv.lib.stack.logic;

import ch.hsr.adv.commons.stack.logic.ConstantsStack;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.stack.logic.domain.ADVStack;
import ch.hsr.adv.lib.stack.logic.domain.StackElement;
import com.google.inject.Singleton;

import java.util.Stack;


/**
 * Builder Implementation for stack module.It builds a ModuleGroup containing
 * the module data.
 * <p>
 * Can only handle stack module!
 */
@Singleton
@Module(ConstantsStack.MODULE_NAME)
class StackBuilder implements Builder {

    @Override
    public ModuleGroup build(ADVModule module) {
        StackModule stackModule = (StackModule) module;
        ModuleGroup moduleGroup = new ModuleGroup(stackModule.getModuleName());

        Stack clonedStack = new Stack();
        ADVStack originalStack = stackModule.getStack();
        int size = originalStack.size();
        for (int i = 0; i < size; i++) {
            Object element = originalStack.pop();
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
