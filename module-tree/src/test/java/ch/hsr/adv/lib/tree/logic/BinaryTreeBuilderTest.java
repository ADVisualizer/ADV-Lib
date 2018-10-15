package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.lib.tree.logic.domain.BinaryTreeTestModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
