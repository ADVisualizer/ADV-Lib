package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.binarytree.BinaryTreeBuilder;
import ch.hsr.adv.lib.tree.logic.binarytree.BinaryTreeModule;
import ch.hsr.adv.lib.tree.logic.domain.BinaryTreeTestModule;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.NodeFixationException;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;
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

    @Test
    public void rootIsNullTest() {
        BinaryTreeModule binaryTreeModule = new BinaryTreeModule(null);

        ModuleGroup nodeGroup = sut.build(binaryTreeModule);

        assertEquals(0, nodeGroup.getRelations().size());
        assertEquals(0, nodeGroup.getElements().size());
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

        int numberOfElements = 3;
        int elementOffset = 1;
        int expectedArraySize = numberOfElements + elementOffset;

        sut.build(binaryTreeModule);
        ArrayModule nodeArray =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);

        assertEquals(expectedArraySize, nodeArray.getArray().length);
    }

    @Test
    public void nodeArrayHasElementsTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);
        int rootRank = 1;

        sut.build(binaryTreeModule);
        ArrayModule nodeArray =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);

        assertNotNull(nodeArray.getArray()[rootRank]);
        assertNotNull(nodeArray.getArray()[rootRank * 2]);
        assertNotNull(nodeArray.getArray()[rootRank * 2 + 1]);
    }

    @Test
    public void nodeArrayHiddenTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        sut.build(binaryTreeModule);

        assertEquals(0,
                binaryTreeModule.getChildModules().size());
    }

    @Test
    public void multipleSnapshotWithoutArrayTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        sut.build(binaryTreeModule);
        int arraySizeFirstRound = binaryTreeModule.getChildModules().size();
        sut.build(binaryTreeModule);
        int arraySizeSecondRound = binaryTreeModule.getChildModules().size();

        assertEquals(0, arraySizeFirstRound);
        assertEquals(0, arraySizeSecondRound);
    }

    @Test
    public void setNewRootTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        ADVBinaryTreeNode<?> root = binaryTreeModule.getRoot();

        sut.build(binaryTreeModule);

        binaryTreeModule.setRoot(root.getLeftChild());
        ModuleGroup moduleGroup = sut.build(binaryTreeModule);

        assertEquals(1, moduleGroup.getElements().size());
    }

    @Test
    public void setNewRootNullTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);
        sut.build(binaryTreeModule);
        binaryTreeModule.setRoot(null);
        sut.build(binaryTreeModule);

        ArrayModule nodeArray =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);

        assertEquals(2, nodeArray.getArray().length);
    }

    @Test
    public void multipleSnapshotWithArrayTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);

        sut.build(binaryTreeModule);
        int arraySizeFirstRound = binaryTreeModule.getChildModules().size();
        sut.build(binaryTreeModule);
        int arraySizeSecondRound = binaryTreeModule.getChildModules().size();

        assertEquals(1, arraySizeFirstRound);
        assertEquals(1, arraySizeSecondRound);
    }

    @Test
    public void multipleSnapshotTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        sut.build(binaryTreeModule);
        int arraySizeFirstRound = binaryTreeModule.getChildModules().size();
        binaryTreeModule.setShowArray(true);
        sut.build(binaryTreeModule);
        int arraySizeSecondRound = binaryTreeModule.getChildModules().size();
        binaryTreeModule.setShowArray(false);
        sut.build(binaryTreeModule);
        int arraySizeThirdRound = binaryTreeModule.getChildModules().size();
        binaryTreeModule.setShowArray(true);
        sut.build(binaryTreeModule);
        int arraySizeFourthRound = binaryTreeModule.getChildModules().size();

        assertEquals(0, arraySizeFirstRound);
        assertEquals(1, arraySizeSecondRound);
        assertEquals(0, arraySizeThirdRound);
        assertEquals(1, arraySizeFourthRound);
    }

    @Test
    public void fixedHeightsNotSetTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();

        ModuleGroup moduleGroup = sut.build(binaryTreeModule);

        assertNull(moduleGroup.getMetaData().get(ConstantsTree.MAX_TREE_HEIGHT_LEFT));
        assertNull(moduleGroup.getMetaData().get(ConstantsTree.MAX_TREE_HEIGHT_RIGHT));
    }

    @Test
    public void fixedHeightProperlySetTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setFixedTreeHeight(1, 1);

        ModuleGroup moduleGroup = sut.build(binaryTreeModule);
        TreeHeightHolder maxTreeHeights = binaryTreeModule.getMaxTreeHeights();
        String leftHeight =
                moduleGroup.getMetaData().get(ConstantsTree.MAX_TREE_HEIGHT_LEFT);
        String rightHeight =
                moduleGroup.getMetaData().get(ConstantsTree.MAX_TREE_HEIGHT_RIGHT);

        assertNotNull(leftHeight);
        assertNotNull(rightHeight);
        assertEquals(maxTreeHeights.getLeftHeight(),
                Integer.parseInt(leftHeight));
        assertEquals(maxTreeHeights.getRightHeight(),
                Integer.parseInt(rightHeight));
    }

    @Test(expected = NodeFixationException.class)
    public void fixedHeightNotProperlySetTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setFixedTreeHeight(0, 0);

        sut.build(binaryTreeModule);
    }

    @Test(expected = NodeFixationException.class)
    public void fixedHeightLeftHeightNotProperlySetTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setFixedTreeHeight(0, 1);

        sut.build(binaryTreeModule);
    }

    @Test(expected = NodeFixationException.class)
    public void fixedHeightRightHeightNotProperlySetTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setFixedTreeHeight(1, 0);

        sut.build(binaryTreeModule);
    }

    @Test
    public void arrayModulePositionTest() {
        BinaryTreeTestModule binaryTreeModule = new BinaryTreeTestModule();
        binaryTreeModule.setShowArray(true);

        sut.build(binaryTreeModule);

        ArrayModule arrayModule =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);

        assertEquals(ConstantsTree.ARRAY_MODULE_VISUALISATION_POSITION,
                arrayModule.getPosition());
    }
}
