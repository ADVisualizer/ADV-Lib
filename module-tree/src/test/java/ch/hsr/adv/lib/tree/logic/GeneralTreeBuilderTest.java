package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ADVElement;
import ch.hsr.adv.commons.core.logic.domain.ADVRelation;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.tree.logic.domain.GeneralTreeTestModule;
import ch.hsr.adv.lib.tree.logic.generaltree.GeneralTreeBuilder;
import ch.hsr.adv.lib.tree.logic.generaltree.GeneralTreeModule;
import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JukitoRunner.class)
public class GeneralTreeBuilderTest {

    @Inject
    private GeneralTreeBuilder sut;

    @Test
    public void createModuleGroupTest() {
        GeneralTreeModule generalTreeModule = new GeneralTreeTestModule();

        ModuleGroup nodeGroup = sut.build(generalTreeModule);

        assertNotNull(nodeGroup);
    }

    @Test
    public void numberOfNodesTest() {
        int expectedNumberOfNodes =
                2 * GeneralTreeTestModule.MAX_NUMBER_OF_CHILDREN_PER_NODE;
        GeneralTreeModule generalTreeModule = new GeneralTreeTestModule();

        ModuleGroup nodeGroup = sut.build(generalTreeModule);

        assertEquals(expectedNumberOfNodes, nodeGroup.getElements().size());
    }

    @Test
    public void numberOfRelationsTest() {
        int expectedNumberOfRelations =
                2 * GeneralTreeTestModule.MAX_NUMBER_OF_CHILDREN_PER_NODE - 1;
        GeneralTreeModule generalTreeModule = new GeneralTreeTestModule();

        ModuleGroup nodeGroup = sut.build(generalTreeModule);

        assertEquals(expectedNumberOfRelations,
                nodeGroup.getRelations().size());
    }

    @Test
    public void correctRelationsTest() {
        GeneralTreeModule generalTreeModule = new GeneralTreeTestModule();

        ModuleGroup nodeGroup = sut.build(generalTreeModule);
        long rootId = nodeGroup.getElements().get(0).getId();
        long secondChildId = nodeGroup.getElements().get(2).getId();
        int numberOfChildrenWithRootParent =
                GeneralTreeTestModule.MAX_NUMBER_OF_CHILDREN_PER_NODE;
        int numberOfChildrenWithSecondChildParent =
                GeneralTreeTestModule.MAX_NUMBER_OF_CHILDREN_PER_NODE - 1;
        int rootParentCount = 0;
        int secondChildCount = 0;

        for (ADVRelation<?> relation :
                nodeGroup.getRelations()) {
            if (relation.getSourceElementId() == rootId) {
                rootParentCount++;
            }

            if (relation.getSourceElementId() == secondChildId) {
                secondChildCount++;
            }
        }

        assertEquals(numberOfChildrenWithRootParent, rootParentCount);
        assertEquals(numberOfChildrenWithSecondChildParent, secondChildCount);
    }

    @Test
    public void correctElementsTest() {
        GeneralTreeModule generalTreeModule = new GeneralTreeTestModule();
        int elementOffset = 1;

        ModuleGroup nodeGroup = sut.build(generalTreeModule);

        for (int i = 0; i < nodeGroup.getElements().size(); i++) {
            ADVElement<?> element = nodeGroup.getElements().get(i);
            assertEquals(i + elementOffset, element.getId());
        }
    }
}
