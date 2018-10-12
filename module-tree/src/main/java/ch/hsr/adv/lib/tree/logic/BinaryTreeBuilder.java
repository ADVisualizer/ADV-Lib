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

    private static final long START_ID = 1;

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
        moduleGroup.addElement(new TreeNodeElement(root, START_ID));
        traverse(moduleGroup, START_ID, root.getLeftChild(), START_ID + 1);
        traverse(moduleGroup, START_ID, root.getRightChild(), START_ID + 2);
    }

    private void traverse(ModuleGroup moduleGroup, long parentId,
                          ADVBinaryTreeNode<?> childNode, long childId) {
        if (childNode != null) {
            logger.debug("Child-Node: " + childNode.toString());
            final long leftChildId = childId + 1;
            final long rightChildId = childId + 2;

            moduleGroup.addElement(new TreeNodeElement(childNode, childId));
            moduleGroup.addRelation(new TreeNodeRelation(parentId,
                    childId, childNode.getStyle()));

            traverse(moduleGroup, childId, childNode.getLeftChild(),
                    leftChildId);
            traverse(moduleGroup, childId, childNode.getRightChild(),
                    rightChildId);
        }
    }

//    private void traverse(ModuleGroup moduleGroup,
//                          ADVBinaryTreeNode<?> parentNode, long currentId) {
//        moduleGroup.addElement(new TreeNodeElement(parentNode, currentId));
//        logger.debug("Current Node: " + parentNode.toString());
//        if (parentNode.getLeftChild() != null) {
//            final long leftChildId = currentId + 1;
//
//            moduleGroup.addRelation(new TreeNodeRelation(currentId,
//                    leftChildId, parentNode.getStyle()));
//            traverse(moduleGroup, parentNode, leftChildId);
//        }
//        if (parentNode.getRightChild() != null) {
//            final long rightChildId = currentId + 2;
//
//            moduleGroup.addRelation(new TreeNodeRelation(currentId,
//                    rightChildId, parentNode.getStyle()));
//            traverse(moduleGroup, parentNode, rightChildId);
//        }
//    }
}
