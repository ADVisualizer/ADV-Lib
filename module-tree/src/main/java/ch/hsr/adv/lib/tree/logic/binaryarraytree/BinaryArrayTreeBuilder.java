package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.binaryarraytree.domain.ArrayTreeNode;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;
import ch.hsr.adv.lib.tree.logic.util.BinaryBuilderUtility;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Builder Strategy for BinaryArrayTreeModule. It builds a ModuleGroup
 * containing the module data. Can only handle binary trees!
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_BINARY_ARRAY_TREE)
public class BinaryArrayTreeBuilder implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            BinaryArrayTreeBuilder.class);

    private static final int START_RANK = 1;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_BINARY_ARRAY_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building Modulegroup from BinaryTreeModule");

            BinaryArrayTreeModule<?> module =
                    (BinaryArrayTreeModule<?>) advModule;
            ModuleGroup moduleGroup =
                    new ModuleGroup(ConstantsTree.MODULE_NAME_BINARY_TREE);
            Object[] nodeArray = module.getModuleNodeArray();

            if (hasRoot(nodeArray)) {
                buildNodes(module, moduleGroup, nodeArray);
            }

            appendMaxTreeHeights(module, moduleGroup, nodeArray);

            if (module.isShowArray()) {
                moduleGroup.getFlags()
                        .add(ConstantsTree.SHOW_ARRAY_INDICES);
                module.appendArrayToModule();
            } else {
                module.removeArrayModule();
            }

            return moduleGroup;
        } else {
            return null;
        }
    }

    private boolean hasRoot(Object[] nodeArray) {
        return nodeArray.length >= 2 && nodeArray[1] != null;
    }

    private void appendMaxTreeHeights(BinaryArrayTreeModule<?> module,
                                      ModuleGroup moduleGroup,
                                      Object[] nodeArray) {
        if (module.getMaxTreeHeights().isSet()) {
            TreeHeightHolder actualTreeHeight = new TreeHeightHolder();
            int leftChildRank = 2 * START_RANK;
            int rightChildRank = 2 * START_RANK + 1;
            actualTreeHeight
                    .setLeftHeight(getTreeHeight(nodeArray, leftChildRank) + 1);
            actualTreeHeight
                    .setRightHeight(getTreeHeight(
                            nodeArray, rightChildRank) + 1);

            BinaryBuilderUtility.appendMaxTreeHeights(moduleGroup,
                    actualTreeHeight,
                    module.getMaxTreeHeights(), logger);
        }
    }

    private int getTreeHeight(Object[] nodeArray, int rank) {
        if (rank >= nodeArray.length || nodeArray[rank] == null) {
            return -1;
        }

        int leftChildRank = 2 * rank;
        int rightChildRank = 2 * rank + 1;

        return Math.max(1 + getTreeHeight(nodeArray, leftChildRank),
                1 + getTreeHeight(nodeArray, rightChildRank));
    }

    private void buildNodes(BinaryArrayTreeModule<?> module,
                            ModuleGroup moduleGroup, Object[] nodeArray) {
        Deque<NodeInformationHolder<ArrayTreeNode>> stack = new ArrayDeque<>();
        moduleGroup.addElement(new TreeNodeElement(
                new ArrayTreeNode(nodeArray[START_RANK],
                        module.getStyles().get(START_RANK)), START_RANK));

        int parentRank = START_RANK;

        NodeInformationHolder<ArrayTreeNode> nextElement =
                getNextElement(module, nodeArray, stack, parentRank);

        while (nextElement != null) {
            moduleGroup.addElement(new TreeNodeElement(
                    nextElement.getChildNode(),
                    nextElement.getChildRank()));

            moduleGroup.addRelation(new TreeNodeRelation(
                    nextElement.getParentRank(), nextElement.getChildRank(),
                    nextElement.getChildNode().getStyle()));

            parentRank = (int) nextElement.getChildRank();
            nextElement = getNextElement(module, nodeArray, stack, parentRank);
        }
    }

    private NodeInformationHolder<ArrayTreeNode> getNextElement(
            BinaryArrayTreeModule<?> module, Object[] nodeArray,
            Deque<NodeInformationHolder<ArrayTreeNode>> stack, int parentRank) {
        int leftChildRank = 2 * parentRank;
        int rightChildRank = 2 * parentRank + 1;

        if (rightChildRank < nodeArray.length
                && nodeArray[rightChildRank] != null) {
            stack.addFirst(new NodeInformationHolder<>(parentRank,
                    rightChildRank,
                    new ArrayTreeNode(nodeArray[rightChildRank],
                            module.getStyles().get(rightChildRank))));
        }

        if (leftChildRank < nodeArray.length
                && nodeArray[leftChildRank] != null) {
            return getNextLeftChildElement(module, nodeArray, leftChildRank);
        } else {
            return getNextParentRightChildElement(stack);
        }
    }

    private NodeInformationHolder<ArrayTreeNode> getNextParentRightChildElement(
            Deque<NodeInformationHolder<ArrayTreeNode>> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.removeFirst();
    }

    private NodeInformationHolder<ArrayTreeNode> getNextLeftChildElement(
            BinaryArrayTreeModule<?> module, Object[] nodeArray,
            int leftChildRank) {
        int parentRank = leftChildRank / 2;

        return new NodeInformationHolder<>(parentRank, leftChildRank,
                new ArrayTreeNode(nodeArray[leftChildRank],
                        module.getStyles().get(leftChildRank)));
    }
}
