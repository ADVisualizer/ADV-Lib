package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.tree.logic.BinaryTreeModule;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class BinaryTreeTestModule extends BinaryTreeModule {

    private final static String SESSION_NAME = "TestBinaryTreeSession";
    private final static ADVBinaryTreeNode<Object> testRoot =
            new ADVBinaryTreeNode<>() {
                @SuppressWarnings("unchecked")
                private ADVBinaryTreeNode<Object> getMockWithContentAndStyle() {
                    ADVBinaryTreeNode<Object> mock =
                            Mockito.mock(ADVBinaryTreeNode.class);
                    when(mock.getContent()).thenReturn(Mockito.mock(Object.class));
                    when(mock.getStyle()).thenReturn(Mockito.mock(ADVStyle.class));

                    return mock;
                }

                @Override
                public ADVBinaryTreeNode<Object> getLeftChild() {
                    return getMockWithContentAndStyle();
                }

                @Override
                public ADVBinaryTreeNode<Object> getRightChild() {
                    return getMockWithContentAndStyle();
                }

                @Override
                public ADVStyle getStyle() {
                    return Mockito.mock(ADVStyle.class);
                }

                @Override
                public Object getContent() {
                    return "testRoot";
                }
            };

    public BinaryTreeTestModule() {
        super(testRoot, SESSION_NAME);
    }
}
