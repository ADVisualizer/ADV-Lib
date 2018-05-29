package ch.hsr.adv.lib.stack.logic.domain;

import ch.hsr.adv.lib.core.logic.domain.styles.ADVEnumStyle;
import ch.hsr.adv.lib.stack.logic.StackModule;
import ch.hsr.adv.lib.stack.logic.exceptions.EmptyStackException;

public class StackTestModule extends StackModule {

    private final static ADVStack<String> stack = new ADVStack<>() {
        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public String top() throws EmptyStackException {
            return TestConstants.ELEMENT_NAME;
        }

        @Override
        public void push(String element) {

        }

        @Override
        public String pop() throws EmptyStackException {
            return TestConstants.ELEMENT_NAME;
        }
    };

    public StackTestModule() {
        super(TestConstants.SESSION_NAME, stack);
        // create test data
        getStyleMap().put(0, new ADVEnumStyle());
        getStyleMap().put(1, new ADVEnumStyle());
    }
}
