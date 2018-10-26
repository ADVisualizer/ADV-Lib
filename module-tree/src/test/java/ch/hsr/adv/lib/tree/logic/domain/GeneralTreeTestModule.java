package ch.hsr.adv.lib.tree.logic.domain;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.tree.logic.generaltree.GeneralTreeModule;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GeneralTreeTestModule extends GeneralTreeModule {

    private final static String SESSION_NAME = "TestGeneralTreeSession";
    public final static int MAX_NUMBER_OF_CHILDREN_PER_NODE = 5;
    private final static ADVGeneralTreeNode<Object> testRoot =
            new ADVGeneralTreeNode<>() {
                private List<ADVGeneralTreeNode<Object>> children =
                        new ArrayList<>();
                private List<ADVGeneralTreeNode<Object>> childrenOfSecondChild = new ArrayList<>();

                @SuppressWarnings("unchecked")
                private void addChildrenToList(List<ADVGeneralTreeNode<Object>> childList,
                                               int numberOfChildren) {
                    for (int i = 1; i <= numberOfChildren; i++) {
                        ADVGeneralTreeNode<Object> child =
                                Mockito.mock(ADVGeneralTreeNode.class);
                        when(child.getContent()).thenReturn("child " + i);
                        when(child.getStyle()).thenReturn(Mockito.mock(ADVStyle.class));

                        childList.add(child);
                    }
                }


                private List<ADVGeneralTreeNode<Object>> getChildrenWithContentAndStyle() {
                    if (children.isEmpty()) {
                        addChildrenToList(children,
                                MAX_NUMBER_OF_CHILDREN_PER_NODE);
                        addChildrenToList(childrenOfSecondChild,
                                MAX_NUMBER_OF_CHILDREN_PER_NODE - 1);

                        when(children.get(1).getChildren()).thenReturn(childrenOfSecondChild);
                    }

                    return children;
                }

                @Override
                public List<ADVGeneralTreeNode<Object>> getChildren() {
                    return getChildrenWithContentAndStyle();
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


    public GeneralTreeTestModule() {
        super(testRoot, SESSION_NAME);
    }
}
