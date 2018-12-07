package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ADVRelation;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.ModulePosition;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.core.logic.domain.styles.presets.ADVSuccessStyle;
import ch.hsr.adv.lib.tree.logic.collectiontree.CollectionTreeBuilder;
import ch.hsr.adv.lib.tree.logic.collectiontree.CollectionTreeModule;
import ch.hsr.adv.lib.tree.logic.domain.GeneralTreeTestNode;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.MultipleParentsException;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class CollectionTreeBuilderTest {

    private static final ADVStyle TEST_STYLE = new ADVSuccessStyle();

    @Inject
    private CollectionTreeBuilder sut;

    @Test
    public void createdModuleGroupIsNotNullTest() {
        CollectionTreeModule<String> module = new CollectionTreeModule<>("tm");
        ModuleGroup moduleGroup = sut.build(module);

        assertNotNull(moduleGroup);
    }

    @Test
    public void moduleGroupContainsAllNodesTest() {
        ModuleGroup moduleGroup = buildModuleGroup();

        final int expectedNodes = 9;
        assertEquals(expectedNodes, moduleGroup.getElements().size());
    }

    @Test
    public void moduleGroupContainsAllRelationsTest() {
        ModuleGroup moduleGroup = buildModuleGroup();

        final int expectedRelations = 6;
        assertEquals(expectedRelations, moduleGroup.getRelations().size());
    }

    @Test
    public void removedNodeIsNotInModuleGroupTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        CollectionTreeModule<String> module = new CollectionTreeModule<>("tm");
        module.add(nodes);
        module.remove(nodes[2]);
        ModuleGroup moduleGroup = sut.build(module);

        final int expectedSize = 8;
        assertEquals(expectedSize, moduleGroup.getElements().size());
    }

    @Test
    public void elementHasCorrectStyleTest() {
        ModuleGroup moduleGroup = buildModuleGroup();
        ADVElement<?> nodeD = findADVElement(moduleGroup.getElements(), "D");

        assertEquals(TEST_STYLE, nodeD.getStyle());
    }

    @Test
    public void relationHasSameStyleAsTargetElementTest() {
        ModuleGroup moduleGroup = buildModuleGroup();
        ADVElement<?> nodeD = findADVElement(moduleGroup.getElements(), "D");
        ADVRelation<?> toD = findADVRelation(moduleGroup.getRelations(),
                nodeD.getId());

        assertEquals(TEST_STYLE, toD.getStyle());
    }

    @Test
    public void noRelationsWithDefaultParentTest() {
        final long defaultParentId = -1;
        ModuleGroup moduleGroup = buildModuleGroup();

        for (ADVRelation<?> relation : moduleGroup.getRelations()) {
            assertNotEquals(defaultParentId, relation.getSourceElementId());
        }
    }

    @Test
    public void relationsAreCorrectTest() {
        ModuleGroup moduleGroup = buildModuleGroup();
        ADVElement<?> child = findADVElement(moduleGroup.getElements(), "H");
        ADVRelation<?> relation = findADVRelation(moduleGroup.getRelations(),
                child.getId());

        ADVElement<?> expectedParent = findADVElement(moduleGroup.getElements(),
                "G");
        assertEquals(expectedParent.getId(), relation.getSourceElementId());
    }

    @Test(expected = CyclicNodeException.class)
    public void inTreeCycleCausesExceptionTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        nodes[6].addChild(nodes[5]);

        buildModuleGroup(nodes);
    }

    @Test(expected = CyclicNodeException.class)
    public void selfReferencingNodeCausesExceptionTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        nodes[0].addChild(nodes[0]);

        buildModuleGroup(nodes);
    }

    @Test(expected = MultipleParentsException.class)
    public void multipleParentsCausesExceptionTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        nodes[8].addChild(nodes[4]);

        buildModuleGroup(nodes);
    }

    @Test(expected = MultipleParentsException.class)
    public void sameChildTwiceCausesExceptionTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        nodes[1].addChild(nodes[2]);

        buildModuleGroup(nodes);
    }

    @Test
    public void rootsKeepOrderTest() {
        ModuleGroup moduleGroup = buildModuleGroup();
        String[] expectedOrder = {"B", "F", "C", "D", "E", "A", "G", "H", "I"};

        for (int i = 0; i < moduleGroup.getElements().size(); i++) {
            assertEquals(expectedOrder[i],
                    moduleGroup.getElements().get(i).getContent());
        }
    }

    @Test
    public void childrenAreNullTest() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        nodes[5].setChildren(null);
        int rankNode5 = 6;
        int expectedSourceRelations = 0;

        ModuleGroup moduleGroup = buildModuleGroup(nodes);

        assertEquals(expectedSourceRelations,
                moduleGroup.getRelations().stream()
                        .filter(relation -> relation.getSourceElementId() == rankNode5).count());
    }

    @SuppressWarnings("rawtypes")
    private ADVElement<?> findADVElement(
            List<ADVElement> elements, String content) {
        return elements.stream().filter(e -> e.getContent().toString().equals(content))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("rawtypes")
    private ADVRelation<?> findADVRelation(
            List<ADVRelation> relations, long targetElementId) {
        return relations.stream().filter(r -> r.getTargetElementId()
                == targetElementId).findFirst().orElse(null);
    }

    private ModuleGroup buildModuleGroup() {
        GeneralTreeTestNode[] nodes = generateTestTree();
        return buildModuleGroup(nodes);
    }

    private ModuleGroup buildModuleGroup(GeneralTreeTestNode[] nodes) {
        CollectionTreeModule<String> module = new CollectionTreeModule<>("tm");
        module.add(nodes);
        return sut.build(module);
    }

    private GeneralTreeTestNode[] generateTestTree() {
        GeneralTreeTestNode[] nodes = new GeneralTreeTestNode[9];
        nodes[0] = new GeneralTreeTestNode("B");
        nodes[1] = new GeneralTreeTestNode("F");
        nodes[2] = new GeneralTreeTestNode("C");
        nodes[3] = new GeneralTreeTestNode("D", TEST_STYLE);
        nodes[4] = new GeneralTreeTestNode("E");
        nodes[5] = new GeneralTreeTestNode("A");
        nodes[6] = new GeneralTreeTestNode("G");
        nodes[7] = new GeneralTreeTestNode("H");
        nodes[8] = new GeneralTreeTestNode("I");
        nodes[1].addChild(nodes[2]);
        nodes[1].addChild(nodes[3]);
        nodes[1].addChild(nodes[4]);
        nodes[5].addChild(nodes[6]);
        nodes[6].addChild(nodes[7]);
        nodes[6].addChild(nodes[8]);

        return nodes;
    }

    @Test
    public void modulePositionAppendedTest() {
        CollectionTreeModule<Character> treeModule =
                new CollectionTreeModule<>("TestModule");
        treeModule.setPosition(ModulePosition.RIGHT);

        ModuleGroup treeGroup = sut.build(treeModule);

        assertEquals(ModulePosition.RIGHT, treeGroup.getPosition());
    }
}
