package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.TreeBuilderBase;
import ch.hsr.adv.lib.tree.logic.exception.NodeFixationException;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
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
public class BinaryTreeBuilder extends TreeBuilderBase implements Builder {

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

            if (root != null) {
                int treeHeight = getTreeHeight(root);

                if (module.getMaxTreeHeights().isSet()) {
                    appendMaxTreeHeights(module, moduleGroup, treeHeight);
                }

                String[] array = createNodeArray(treeHeight);
                module.appendArrayToModule(array);
                buildNodes(root, moduleGroup, module);

                if (module.isShowArray()) {
                    moduleGroup.getFlags()
                            .add(ConstantsTree.SHOW_ARRAY_INDICES);
                } else {
                    module.removeArrayModule();
                }
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

    private void appendMaxTreeHeights(BinaryTreeModule module,
                                      ModuleGroup moduleGroup, int treeHeight) {
        int leftHeight = module.getMaxTreeHeights().getMaxLeftHeight();
        int rightHeight = module.getMaxTreeHeights().getMaxRightHeight();

        if (treeHeight >= Math.max(leftHeight, rightHeight)) {
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_LEFT,
                            String.valueOf(leftHeight));
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_RIGHT,
                            String.valueOf(rightHeight));
        } else {
            String errorMessage = "The maximum tree height ("
                    + treeHeight + ") exceeds the set maximum left ("
                    + leftHeight + ") or right tree height (" + rightHeight
                    + "). You must set the correct maximum left or right tree"
                    + " height to actual tree height";
            logger.error(errorMessage);
            throw new NodeFixationException(errorMessage);

        }
    }

    private int getTreeHeight(ADVBinaryTreeNode<?> root) {
        final Set<ADVTreeNode<?>> visitedNodes = new HashSet<>();
        return getTreeHeight(root, visitedNodes);
    }

    private String[] createNodeArray(int treeHeight) {
        int maxNumberOfTreeNodes =
                (int) Math.pow(2, treeHeight + 1);
        return new String[maxNumberOfTreeNodes];
    }

    private int getTreeHeight(ADVBinaryTreeNode<?> node,
                              Set<ADVTreeNode<?>> visitedNodes) {
        if (node == null) {
            return -1;
        }

        checkCyclicNode(visitedNodes, -1, node);

        return Math.max(1 + getTreeHeight(node.getLeftChild(), visitedNodes),
                1 + getTreeHeight(node.getRightChild(), visitedNodes));
    }

    private void buildNodes(ADVBinaryTreeNode<?> root,
                            ModuleGroup moduleGroup,
                            ADVModule binaryTreeModule) {
        final Set<ADVTreeNode<?>> visitedNodes = new HashSet<>();
        logger.debug("Current Node: " + root.toString());
        visitedNodes.add(root);
        addNodeToArray(root, binaryTreeModule, (int) START_RANK);
        moduleGroup.addElement(new TreeNodeElement(root, START_RANK));

        buildChildren(moduleGroup, binaryTreeModule, visitedNodes, START_RANK,
                root);
    }

    private void addNodeToArray(ADVBinaryTreeNode<?> node,
                                ADVModule binaryTreeModule, int rank) {
        ArrayModule nodeArrayModule =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);
        String[] nodeArray = (String[]) nodeArrayModule.getArray();
        nodeArray[rank] = node.getContent().toString();

        nodeArrayModule.getStyleMap().put(rank, node.getStyle());
    }

    private void buildNode(ModuleGroup moduleGroup,
                           NodeInformationHolder<ADVBinaryTreeNode<?>>
                                   childNodeInformation,
                           ADVModule binaryTreeModule,
                           Set<ADVTreeNode<?>> visitedNodes) {
        if (childNodeInformation.getChildNode() != null) {
            logger.debug("Child-Node: " + childNodeInformation.getChildNode()
                    .toString());

            checkCyclicNode(visitedNodes,
                    childNodeInformation.getParentRank(),
                    childNodeInformation.getChildNode());

            addNodeToArray(childNodeInformation.getChildNode(),
                    binaryTreeModule,
                    (int) childNodeInformation.getChildRank());

            addNodeToModuleGroup(moduleGroup, childNodeInformation);

            buildChildren(moduleGroup, binaryTreeModule, visitedNodes,
                    childNodeInformation.getChildRank(),
                    childNodeInformation.getChildNode());
        }
    }

    private void buildChildren(ModuleGroup moduleGroup,
                               ADVModule binaryTreeModule,
                               Set<ADVTreeNode<?>> visitedNodes,
                               long parentRank,
                               ADVBinaryTreeNode<?> childNode) {
        long leftChildRank = 2 * parentRank;
        long rightChildRank = 2 * parentRank + 1;

        buildNode(moduleGroup,
                new NodeInformationHolder<>(
                        parentRank,
                        leftChildRank,
                        childNode.getLeftChild()),
                binaryTreeModule, visitedNodes);

        buildNode(moduleGroup,
                new NodeInformationHolder<>(
                        parentRank,
                        rightChildRank,
                        childNode.getRightChild()),
                binaryTreeModule, visitedNodes);
    }
}
