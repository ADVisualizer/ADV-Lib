package ch.hsr.adv.lib.queue.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.queue.logic.ConstantsQueue;
import ch.hsr.adv.commons.queue.logic.domain.QueueElement;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.queue.logic.domain.ADVQueue;
import com.google.inject.Singleton;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Builder Strategy for stack module. Builds a ModuleGroup containing
 * the module data.
 * <p>
 * Can only handle stack module!
 */
@Singleton
@Module(ConstantsQueue.MODULE_NAME)
class QueueBuilder implements Builder {

    @Override
    public ModuleGroup build(ADVModule module) {
        ModuleGroup moduleGroup = createModuleGroup(module);
        return moduleGroup;
    }

    private <T> ModuleGroup createModuleGroup(ADVModule module) {
        QueueModule queueModule = (QueueModule) module;
        ModuleGroup moduleGroup = new ModuleGroup(queueModule.getModuleName());

        Queue<T> clonedQueue = new LinkedList<>();
        @SuppressWarnings("unchecked")
        ADVQueue<T> originalQueue = (ADVQueue<T>) queueModule.getQueue();
        int size = originalQueue.size();
        for (int i = 0; i < size; i++) {
            T element = originalQueue.removeMin();
            clonedQueue.add(element);

            QueueElement queueElement = new QueueElement();
            queueElement.setId(i);
            queueElement.setContent(element.toString());

            ADVStyle style = queueModule.getStyleMap().get(i);
            queueElement.setStyle(style);
            moduleGroup.addElement(queueElement);
        }

        for (int i = 0; i < size; i++) {
            originalQueue.insert(clonedQueue.poll());
        }

        return moduleGroup;
    }
}
