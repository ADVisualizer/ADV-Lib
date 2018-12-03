package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.domain.BinaryArrayTreeTestModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class BinaryArrayTreeModuleTest {

    @Test(expected = IllegalArgumentException.class)
    public void arrayIsNullTest() {
        new BinaryArrayTreeTestModule<>((Integer[]) null, "TestSession");
    }

    @Test(expected = IllegalArgumentException.class)
    public void arrayListIsNullTest() {
        new BinaryArrayTreeTestModule<>((ArrayList<String>) null, "TestSession");
    }

    @Test(expected = IllegalArgumentException.class)
    public void noElementArrayTest() {
        ArrayList<String> dummyArray = new ArrayList<>();

        new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
    }

    @Test
    public void properModuleInitializationTest() {
        ArrayList<String> dummyArray = new ArrayList<>();
        dummyArray.add(null);
        dummyArray.add("root");

        BinaryArrayTreeTestModule<String> sut =
                new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
        sut.appendArrayToModule();

        ArrayModule arrayModule = (ArrayModule) sut.getChildModules().get(0);

        assertEquals(dummyArray.size(), arrayModule.getArray().length);
        assertEquals(dummyArray.get(0), arrayModule.getArray()[0]);
        assertEquals(dummyArray.get(1), arrayModule.getArray()[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArrayLengthTest() {
        ArrayList<String> dummyArray = new ArrayList<>();
        dummyArray.add(null);
        dummyArray.add("root");
        dummyArray.add("leftchild");

        new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalStyleSetTest() {
        String[] dummyArray = {null, "root"};
        BinaryArrayTreeTestModule<String> sut =
                new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
        sut.setStyle(2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addChildParentIsNullTest() {
        String[] dummyArray = {null, null};
        int parentIndex = 1;
        BinaryArrayTreeTestModule<String> sut =
                new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
        sut.addLeftChild(parentIndex, "leftchild");
    }

    @Test
    public void addChildArrayMustGrowTest() {
        String[] dummyArray = {null, "root"};
        int expectedSize = dummyArray.length * 2;
        int parentIndex = 1;
        int rightChild = 2 * parentIndex + 1;
        BinaryArrayTreeTestModule<String> sut =
                new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
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
        BinaryArrayTreeTestModule<String> sut =
                new BinaryArrayTreeTestModule<>(dummyArray, "TestSession");
        sut.addLeftChild(parentIndex, "leftchild");

        String[] newArray = sut.getModuleNodeArray();

        assertEquals(expectedSize, newArray.length);
        assertEquals("leftchild", newArray[leftChild]);
    }
}
