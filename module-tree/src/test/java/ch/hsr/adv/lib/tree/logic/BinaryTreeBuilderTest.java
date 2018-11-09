package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.binarytree.BinaryTreeBuilder;
import ch.hsr.adv.lib.tree.logic.binarytree.BinaryTreeModule;
import ch.hsr.adv.lib.tree.logic.domain.BinaryTreeTestModule;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class BinaryTreeBuilderTest {

    @Inject
    private BinaryTreeBuilder sut;

    @Test
    public void createModuleGroupTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);

        assertNotNull(nodeGroup);
    }

    @Test
    public void numberOfRelationsTest() {
        final int expectedRelations = 2;
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);

        assertEquals(expectedRelations, nodeGroup.getRelations().size());
    }

    @Test
    public void relationHasTheSameStyleAsChildrenTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);
        ADVStyle leftChildStyle = nodeGroup.getElements().get(1).getStyle();
        ADVStyle actualStyle = nodeGroup.getRelations().get(0).getStyle();

        assertEquals(leftChildStyle, actualStyle);
    }

    @Test
    public void childRankTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);
        long rootRank = nodeGroup.getElements().get(0).getId();
        long leftChildRank = nodeGroup.getElements().get(1).getId();
        long rightChildRank = nodeGroup.getElements().get(2).getId();

        assertEquals(2 * rootRank, leftChildRank);
        assertEquals(2 * rootRank + 1, rightChildRank);
    }

    @Test(expected = RootUnspecifiedException.class)
    public void rootIsNullTest() {
        BinaryTreeModule binaryTreeModule = new BinaryTreeModule(null, null);

        sut.build(binaryTreeModule);
    }

    @Test(expected = CyclicNodeException.class)
    public void treeWithCycleTest() {
        BinaryTreeModule binaryTreeModule =
                new BinaryTreeModule(BinaryTreeTestModule.testRootWithCycle,
                        BinaryTreeTestModule.SESSION_NAME);

        sut.build(binaryTreeModule);
    }

    @Test
    public void addArrayIndicesFlagTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);

        assertEquals(ConstantsTree.SHOW_ARRAY_INDICES,
                nodeGroup.getFlags().get(0));
    }

    @Test
    public void nodeArraySizeTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);
        ArrayModule nodeArray =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);
        int numberOfElements = 3;
        int elementOffset = 1;
        int expectedArraySize = numberOfElements + elementOffset;

        sut.build(binaryTreeModule);

        assertEquals(expectedArraySize, nodeArray.getArray().length);
    }

    @Test
    public void nodeArrayHasElementsTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);
        ArrayModule nodeArray =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);
        int rootRank = 1;

        sut.build(binaryTreeModule);

        assertNotNull(nodeArray.getArray()[rootRank]);
        assertNotNull(nodeArray.getArray()[rootRank * 2]);
        assertNotNull(nodeArray.getArray()[rootRank * 2 + 1]);
    }

    @Test
    public void nodeArrayHiddenTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        sut.build(binaryTreeModule);

        assertEquals(0,
                ((ArrayModule) binaryTreeModule.getChildModules().get(0)).getArray().length);
    }

    @Test
    public void multipleSnapshotWithoutArrayTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        ArrayModule arrayModule =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);

        sut.build(binaryTreeModule);
        int arraySizeFirstRound = arrayModule.getArray().length;
        sut.build(binaryTreeModule);
        int arraySizeSecondRound = arrayModule.getArray().length;

        assertEquals(0, arraySizeFirstRound);
        assertEquals(0, arraySizeSecondRound);
    }
}
