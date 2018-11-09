package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.binaryarraytree.BinaryArrayTreeModule;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class BinaryArrayTreeModuleTest {

    @Test(expected = RootUnspecifiedException.class)
    public void illegalArrayRootTest() {
        String[] dummyArray = new String[2];
        new BinaryArrayTreeModule<>(dummyArray, "TestSession");
    }

    @Test(expected = RootUnspecifiedException.class)
    public void noElementArrayTest() {
        ArrayList<String> dummyArray = new ArrayList<>();
        new BinaryArrayTreeModule<>(dummyArray, "TestSession");
    }

    @Test(expected = RootUnspecifiedException.class)
    public void noRootTest() {
        ArrayList<String> dummyArray = new ArrayList<>();
        dummyArray.add(null);
        dummyArray.add(null);
        new BinaryArrayTreeModule<>(dummyArray, "TestSession");
    }

    @Test
    public void properModuleInitializationTest() {
        ArrayList<String> dummyArray = new ArrayList<>();
        dummyArray.add(null);
        dummyArray.add("root");

        BinaryArrayTreeModule<String> sut =
                new BinaryArrayTreeModule<>(dummyArray, "TestSession");

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

        new BinaryArrayTreeModule<>(dummyArray, "TestSession");
    }
}
