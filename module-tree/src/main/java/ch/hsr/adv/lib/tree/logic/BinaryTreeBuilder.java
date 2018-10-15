package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Builder Strategy for BinaryTreeModule. It builds a ModuleGroup
 * containing the module data. Can only handle binary trees!
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_BINARY_TREE)
public class BinaryTreeBuilder implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            BinaryTreeBuilder.class);

    private static final long START_RANK = 1;
    private final Set<ADVBinaryTreeNode<?>> visitedNodes = new HashSet<>();

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_BINARY_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building Modulegroup from BinaryTreeModule");

            visitedNodes.clear();
            BinaryTreeModule module = (BinaryTreeModule) advModule;
            ADVBinaryTreeNode<?> root = module.getRoot();
            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());

            if (root != null) {
                buildNodes(root, moduleGroup);
            } else {
                logger.error("Root Node from the BinaryTreeModule is null");
                throw new RootUnspecifiedException("The root node must not be"
                        + " null");
            }

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildNodes(ADVBinaryTreeNode<?> root,
                            ModuleGroup moduleGroup) {
        logger.debug("Current Node: " + root.toString());
        visitedNodes.add(root);
        moduleGroup.addElement(new TreeNodeElement(root, START_RANK));
        buildNode(moduleGroup, START_RANK, root.getLeftChild(), 2 * START_RANK);
        buildNode(moduleGroup, START_RANK, root.getRightChild(),
                2 * START_RANK + 1);
    }

    private void buildNode(ModuleGroup moduleGroup, long parentRank,
                           ADVBinaryTreeNode<?> childNode, long childRank) {
        if (childNode != null) {
            logger.debug("Child-Node: " + childNode.toString());
            final long leftChildRank = 2 * childRank;
            final long rightChildId = 2 * childRank + 1;

            checkCyclicNode(parentRank, childNode);
            visitedNodes.add(childNode);
            moduleGroup.addElement(new TreeNodeElement(childNode,
                    childRank));

            moduleGroup.addRelation(new TreeNodeRelation(parentRank,
                    childRank, childNode.getStyle()));

            buildNode(moduleGroup, childRank, childNode.getLeftChild(),
                    leftChildRank);
            buildNode(moduleGroup, childRank, childNode.getRightChild(),
                    rightChildId);
        }
    }

    private void checkCyclicNode(long parentRank,
                                 ADVBinaryTreeNode<?> childNode) {
        if (visitedNodes.contains(childNode)) {
            String errorMessage = "the child (" + childNode.toString()
                    + " of Parent with Rank " + parentRank + "is already a "
                    + "node in the tree";
            logger.error(errorMessage);
            throw new CyclicNodeException(errorMessage);
        }
    }
}
