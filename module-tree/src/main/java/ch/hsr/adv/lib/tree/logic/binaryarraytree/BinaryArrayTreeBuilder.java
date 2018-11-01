package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.binaryarraytree.domain.ArrayTreeNode;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
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
            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());
            Object[] nodeArray =
                    ((ArrayModule) module.getChildModules().get(0)).getArray();

            buildNodes(moduleGroup, nodeArray);

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildNodes(ModuleGroup moduleGroup, Object[] nodeArray) {
        Deque<NodeInformationHolder<ArrayTreeNode>> stack = new ArrayDeque<>();
        ArrayTreeNode rootNode = new ArrayTreeNode(nodeArray[START_RANK]);
        moduleGroup.addElement(new TreeNodeElement(rootNode, START_RANK));
        stack.addFirst(new NodeInformationHolder<>(-1, START_RANK, rootNode));
        int parentRank = START_RANK;

        do {
            stack.removeFirst();
            NodeInformationHolder<ArrayTreeNode> nextElement =
                    getNextElement(nodeArray, stack, parentRank);
            if (nextElement != null) {
                stack.addFirst(nextElement);

                moduleGroup.addElement(new TreeNodeElement(
                        nextElement.getChildNode(),
                        nextElement.getChildRank()));

                moduleGroup.addRelation(new TreeNodeRelation(
                        nextElement.getParentRank(), nextElement.getChildRank(),
                        nextElement.getChildNode().getStyle()));
                parentRank = (int) nextElement.getChildRank();
            }
        } while (!stack.isEmpty());
    }

    private NodeInformationHolder<ArrayTreeNode> getNextElement(
            Object[] nodeArray,
            Deque<NodeInformationHolder<ArrayTreeNode>> stack, int parentRank) {
        int leftChildRank = 2 * parentRank;
        int rightChildRank = 2 * parentRank + 1;

        if (rightChildRank < nodeArray.length
                && nodeArray[rightChildRank] != null) {
            stack.addFirst(new NodeInformationHolder<>(parentRank,
                    rightChildRank,
                    new ArrayTreeNode(nodeArray[rightChildRank])));
        }

        if (leftChildRank < nodeArray.length
                && nodeArray[leftChildRank] != null) {
            return getNextLeftChildElement(nodeArray, leftChildRank);
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
            Object[] nodeArray, int leftChildRank) {
        int parentRank = leftChildRank / 2;

        return new NodeInformationHolder<>(parentRank, leftChildRank,
                new ArrayTreeNode(nodeArray[leftChildRank]));
    }
}
