package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.binaryarraytree.BinaryArrayTreeBuilder;
import ch.hsr.adv.lib.tree.logic.binaryarraytree.BinaryArrayTreeModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JukitoRunner.class)
public class BinaryArrayTreeBuilderTest {

    @Inject
    private BinaryArrayTreeBuilder sut;
    private ArrayList<String> dummyArray;
    private BinaryArrayTreeModule<String> module;

    @Before
    public void setUp() {
        dummyArray = new ArrayList<>();
        dummyArray.add(null);
        dummyArray.add("1");
        dummyArray.add("2");
        dummyArray.add("3");
        dummyArray.add(null);
        dummyArray.add("5");
        dummyArray.add("6");
        dummyArray.add(null);
        module = new BinaryArrayTreeModule<>(dummyArray, "TestSession");
    }

    @Test
    public void hasCorrectNumberOfElementsTest() {
        int numberOfElements =
                (int) dummyArray.stream().filter(Objects::nonNull).count();
        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(numberOfElements, moduleGroup.getElements().size());
    }

    @Test
    public void hasCorrectNumberOfRelationsTest() {
        int numberOfRelations =
                (int) dummyArray.stream().filter(Objects::nonNull).count() - 1;

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(numberOfRelations, moduleGroup.getRelations().size());
    }

    @Test
    public void properElementsWithNormalTreeTest() {
        Set<String> elements =
                dummyArray.stream().filter(Objects::nonNull).collect(Collectors.toSet());

        ModuleGroup moduleGroup = sut.build(module);

        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }

    @Test
    public void properElementsWithOnlyRootTest() {
        String[] onlyRoot = {null, "1"};
        Set<String> elements =
                Arrays.stream(onlyRoot).filter(Objects::nonNull).collect(Collectors.toSet());
        BinaryArrayTreeModule<String> module =
                new BinaryArrayTreeModule<>(onlyRoot, "TestSession");

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(elements.size(), moduleGroup.getElements().size());
        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }

    @Test
    public void properElementsWithRightTreeTest() {
        String[] rightTree = new String[8];
        rightTree[1] = "1";
        rightTree[3] = "3";
        rightTree[7] = "7";
        Set<String> elements =
                Arrays.stream(rightTree).filter(Objects::nonNull).collect(Collectors.toSet());
        BinaryArrayTreeModule<String> module =
                new BinaryArrayTreeModule<>(rightTree, "TestSession");

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(elements.size(), moduleGroup.getElements().size());
        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }

    @Test
    public void properElementsWithLeftTreeTest() {
        String[] leftTree = new String[8];
        leftTree[1] = "1";
        leftTree[2] = "2";
        leftTree[4] = "4";
        Set<String> elements =
                Arrays.stream(leftTree).filter(Objects::nonNull).collect(Collectors.toSet());
        BinaryArrayTreeModule<String> module =
                new BinaryArrayTreeModule<>(leftTree, "TestSession");

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(elements.size(), moduleGroup.getElements().size());
        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }

    @Test
    public void multipleSnapshotWithoutArrayTest() {
        sut.build(module);
        int arraySizeFirstRound = module.getChildModules().size();
        sut.build(module);
        int arraySizeSecondRound = module.getChildModules().size();

        assertEquals(0, arraySizeFirstRound);
        assertEquals(0, arraySizeSecondRound);
    }

    @Test
    public void treeHeightChangeTest() {
        ArrayList<String> fullTree = new ArrayList<>();
        fullTree.add(null);
        fullTree.add("1");
        module.setArray(fullTree);

        sut.build(module);

        fullTree.add("2");
        fullTree.add(null);
        module.setArray(fullTree);
        Set<String> elements =
                fullTree.stream().filter(Objects::nonNull).collect(Collectors.toSet());

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(elements.size(), moduleGroup.getElements().size());
        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }

    @Test
    public void removeElementTest() {
        String[] tree = {null, "1", "2", "3"};
        module.setArray(tree);

        sut.build(module);

        tree[3] = null;
        Set<String> elements =
                Arrays.stream(tree).filter(Objects::nonNull).collect(Collectors.toSet());
        module.setArray(tree);

        ModuleGroup moduleGroup = sut.build(module);

        assertEquals(elements.size(), moduleGroup.getElements().size());
        moduleGroup.getElements()
                .forEach(element -> assertTrue(elements.contains(element.getContent().toString())));
    }
}
