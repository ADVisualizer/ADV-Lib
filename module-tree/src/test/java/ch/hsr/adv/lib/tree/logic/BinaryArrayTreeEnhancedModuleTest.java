package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.lib.tree.logic.domain.BinaryArrayTreeTestEnhancedModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class BinaryArrayTreeEnhancedModuleTest {
    @Test(expected = IllegalArgumentException.class)
    public void addChildParentIsNullTest() {
        String[] dummyArray = {null, null};
        int parentIndex = 1;
        BinaryArrayTreeTestEnhancedModule<String> sut =
                new BinaryArrayTreeTestEnhancedModule<>(dummyArray, "TestSession");
        sut.addLeftChild(parentIndex, "leftchild");
    }

    @Test
    public void addChildArrayMustGrowTest() {
        String[] dummyArray = {null, "root"};
        int expectedSize = dummyArray.length * 2;
        int parentIndex = 1;
        int rightChild = 2 * parentIndex + 1;
        BinaryArrayTreeTestEnhancedModule<String> sut =
                new BinaryArrayTreeTestEnhancedModule<>(dummyArray, "TestSession");
        sut.addRightChild(parentIndex, "rightchild");

        String[] newArray = sut.getModuleNodeArray();

        assertEquals(expectedSize, newArray.length);
        assertEquals("rightchild", newArray[rightChild]);
    }

    @Test
    public void addChildWithoutGrowTest() {
        String[] dummyArray = {null, "root", null, null};
        int expectedSize = dummyArray.length;
        int parentIndex = 1;
        int leftChild = 2 * parentIndex;
        BinaryArrayTreeTestEnhancedModule<String> sut =
                new BinaryArrayTreeTestEnhancedModule<>(dummyArray, "TestSession");
        sut.addLeftChild(parentIndex, "leftchild");

        String[] newArray = sut.getModuleNodeArray();

        assertEquals(expectedSize, newArray.length);
        assertEquals("leftchild", newArray[leftChild]);
    }
}
