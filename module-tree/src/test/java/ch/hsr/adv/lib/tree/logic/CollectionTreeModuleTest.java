package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.tree.logic.domain.CollectionTreeTestModule;
import ch.hsr.adv.lib.tree.logic.domain.GeneralTreeTestNode;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class CollectionTreeModuleTest {

    private CollectionTreeTestModule<String> sut;

    @Before
    public void initializeSut() {
        sut = new CollectionTreeTestModule<>("Test-Module");
    }

    @Test
    public void newModuleHasNoNodesTest() {
        assertModuleIsEmpty();
    }

    @Test
    public void addedNodeIsInModuleTest() {
        GeneralTreeTestNode node = new GeneralTreeTestNode();
        sut.add(node);

        assertModuleContains(node);
    }

    @Test
    public void addedNodesWithCollectionAreInModuleTest() {
        Collection<GeneralTreeTestNode> nodes = generateTestNodeList(7);
        sut.add(nodes);

        assertModuleContains(nodes);
    }

    @Test
    public void addedNodesWithArrayAreInModuleTest() {
        GeneralTreeTestNode[] nodes = generateTestNodeArray(5);
        sut.add(nodes);

        assertModuleContains(nodes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullThrowsExceptionTest() {
        sut.add((GeneralTreeTestNode) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullCollectionThrowsExceptionTest() {
        sut.add((Collection<GeneralTreeTestNode>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullArrayThrowsExceptionTest() {
        sut.add((GeneralTreeTestNode[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullInCollectionThrowsExceptionTest() {
        List<GeneralTreeTestNode> nullList = generateTestNodeList(7);
        nullList.set(5, null);
        sut.add(nullList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullInArrayThrowsExceptionTest() {
        GeneralTreeTestNode[] nullArray = generateTestNodeArray(9);
        nullArray[4] = null;
        sut.add(nullArray);
    }

    @Test
    public void removedNodeIsNotInModuleTest() {
        List<GeneralTreeTestNode> nodes = addNodesToModule(8);
        final GeneralTreeTestNode removedNode = nodes.get(3);
        sut.remove(removedNode);
        nodes.remove(removedNode);

        assertModuleContains(nodes);
        assertModuleContainsNot(removedNode);
    }

    @Test
    public void removedNodesWithCollectionAreNotInModuleTest() {
        List<GeneralTreeTestNode> nodes = addNodesToModule(4);
        final GeneralTreeTestNode notRemovedNode = nodes.get(1);
        nodes.remove(notRemovedNode);
        sut.remove(nodes);

        assertModuleContains(notRemovedNode);
        assertModuleContainsNot(nodes);
    }

    @Test
    public void removedNodesWithArrayAreNotInModuleTest() {
        List<GeneralTreeTestNode> nodes = addNodesToModule(10);
        List<GeneralTreeTestNode> removedNodes =
                new ArrayList<>(nodes.subList(2, 5));
        sut.remove(removedNodes.toArray(new GeneralTreeTestNode[3]));
        nodes.removeAll(removedNodes);

        assertModuleContains(nodes);
        assertModuleContainsNot(removedNodes);
    }

    @Test
    public void removeSameCollectionLeavesEmptyModuleTest() {
        List<GeneralTreeTestNode> nodes = addNodesToModule(7);
        sut.remove(nodes);

        assertModuleIsEmpty();
    }

    @Test
    public void removeNotContainedNodeDoesNotChangeModuleTest() {
        final int nodeCount = 9;
        List<GeneralTreeTestNode> nodes = addNodesToModule(nodeCount);
        sut.remove(new GeneralTreeTestNode());

        assertEquals(nodeCount, sut.getNodes().size());
        assertModuleContains(nodes);
    }

    private void assertModuleIsEmpty() {
        assertTrue(sut.getNodes().isEmpty());
    }

    private void assertModuleContains(GeneralTreeTestNode node) {
        assertTrue(sut.getNodes().contains(node));
    }

    private void assertModuleContains(
            Collection<GeneralTreeTestNode> collection) {
        assertTrue(sut.getNodes().containsAll(collection));
    }

    private void assertModuleContains(GeneralTreeTestNode[] array) {
        assertTrue(sut.getNodes().containsAll(Arrays.asList(array)));
    }

    private void assertModuleContainsNot(GeneralTreeTestNode node) {
        assertFalse(sut.getNodes().contains(node));
    }

    private void assertModuleContainsNot(
            Collection<? extends GeneralTreeTestNode> innerCollection) {
        for (GeneralTreeTestNode element : innerCollection) {
            assertFalse(sut.getNodes().contains(element));
        }
    }

    private List<GeneralTreeTestNode> addNodesToModule(int size) {
        List<GeneralTreeTestNode> nodes = generateTestNodeList(size);
        sut.add(nodes);
        return nodes;
    }

    private List<GeneralTreeTestNode> generateTestNodeList(int size) {
        return new ArrayList<>(Arrays.asList(generateTestNodeArray(size)));
    }

    private GeneralTreeTestNode[] generateTestNodeArray(int size) {
        GeneralTreeTestNode[] nodes = new GeneralTreeTestNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new GeneralTreeTestNode();
        }
        return nodes;
    }
}
