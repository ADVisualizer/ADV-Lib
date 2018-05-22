package ch.hsr.adv.lib.queue.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVEnumStyle;
import ch.hsr.adv.lib.queue.logic.QueueModule;
import ch.hsr.adv.lib.queue.logic.exception.EmptyQueueException;

public class QueueTestModule extends QueueModule {

    private final static ADVQueue<String> stack = new ADVQueue<String>() {
        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public String min() throws EmptyQueueException {
            return TestConstants.ELEMENT_NAME;
        }

        @Override
        public void insert(String element) {

        }

        @Override
        public String removeMin() throws EmptyQueueException {
            return TestConstants.ELEMENT_NAME;
        }
    };

    public QueueTestModule() {
        super(TestConstants.SESSION_NAME, stack);
        // create test data
        getStyleMap().put(0, new ADVEnumStyle());
        getStyleMap().put(1, new ADVEnumStyle());
    }
}
