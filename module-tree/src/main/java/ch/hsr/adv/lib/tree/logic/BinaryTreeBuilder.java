package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_BINARY_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building Modulegroup from BinaryTreeModule");

            BinaryTreeModule module = (BinaryTreeModule) advModule;
            ADVBinaryTreeNode<?> root = module.getRoot();
            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());

            buildNodes(root, moduleGroup);

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildNodes(ADVBinaryTreeNode<?> root,
                            ModuleGroup moduleGroup) {
        logger.debug("Current Node: " + root.toString());
        moduleGroup.addElement(new TreeNodeElement(root, START_RANK));
        traverse(moduleGroup, START_RANK, root.getLeftChild(), 2 * START_RANK);
        traverse(moduleGroup, START_RANK, root.getRightChild(),
                2 * START_RANK + 1);
    }

    private void traverse(ModuleGroup moduleGroup, long parentRank,
                          ADVBinaryTreeNode<?> childNode, long childRank) {
        if (childNode != null) {
            logger.debug("Child-Node: " + childNode.toString());
            final long leftChildRank = 2 * childRank;
            final long rightChildId = 2 * childRank + 1;

            moduleGroup.addElement(new TreeNodeElement(childNode, childRank));
            moduleGroup.addRelation(new TreeNodeRelation(parentRank,
                    childRank, childNode.getStyle()));

            traverse(moduleGroup, childRank, childNode.getLeftChild(),
                    leftChildRank);
            traverse(moduleGroup, childRank, childNode.getRightChild(),
                    rightChildId);
        }
    }
}
