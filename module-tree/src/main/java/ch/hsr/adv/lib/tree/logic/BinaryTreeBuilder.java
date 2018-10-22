package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
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

            if (module.isShowArrayIndices()) {
                moduleGroup.getFlags().add(ConstantsTree.SHOW_ARRAY_INDICES);
            }

            if (root != null) {
                String[] array = createNodeArray(root);
                buildNodes(root, moduleGroup, module, array);
                addNodeArrayToModule(array, module);
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

    private void addNodeArrayToModule(String[] nodeArray,
                                      BinaryTreeModule module) {
        ArrayModule nodeArrayModule =
                (ArrayModule) module.getChildModules().get(0);

        nodeArrayModule.setArray(nodeArray);
    }

    private String[] createNodeArray(ADVBinaryTreeNode<?> root) {
        final Set<ADVBinaryTreeNode<?>> visitedNodes = new HashSet<>();
        final int treeHeight = getTreeHeight(root, visitedNodes);

        int maxNumberOfTreeNodes =
                (int) Math.pow(2, treeHeight + 1);
        return new String[maxNumberOfTreeNodes];
    }

    private int getTreeHeight(ADVBinaryTreeNode<?> node,
                              Set<ADVBinaryTreeNode<?>> visitedNodes) {
        if (node == null) {
            return -1;
        }

        checkCyclicNode(visitedNodes, -1, node);

        return Math.max(1 + getTreeHeight(node.getLeftChild(), visitedNodes),
                1 + getTreeHeight(node.getRightChild(), visitedNodes));
    }

    private void buildNodes(ADVBinaryTreeNode<?> root,
                            ModuleGroup moduleGroup,
                            ADVModule binaryTreeModule, String[] nodeArray) {
        final Set<ADVBinaryTreeNode<?>> visitedNodes = new HashSet<>();
        logger.debug("Current Node: " + root.toString());
        visitedNodes.add(root);
        addNodeToArray(root, nodeArray, binaryTreeModule, (int) START_RANK);
        moduleGroup.addElement(new TreeNodeElement(root, START_RANK));

        buildNode(moduleGroup, new NodeInformationHolder(START_RANK,
                        2 * START_RANK, root.getLeftChild()), binaryTreeModule,
                nodeArray, visitedNodes);
        buildNode(moduleGroup, new NodeInformationHolder(START_RANK,
                        2 * START_RANK + 1, root.getRightChild()),
                binaryTreeModule,
                nodeArray, visitedNodes);
    }

    private void addNodeToArray(ADVBinaryTreeNode<?> node,
                                String[] nodeArray,
                                ADVModule binaryTreeModule, int rank) {
        nodeArray[rank] = node.getContent().toString();
        ArrayModule nodeArrayModule =
                (ArrayModule) binaryTreeModule.getChildModules().get(0);
        nodeArrayModule.getStyleMap().put(rank, node.getStyle());
    }

    private void buildNode(ModuleGroup moduleGroup,
                           NodeInformationHolder childNodeInformation,
                           ADVModule binaryTreeModule, String[] nodeArray,
                           Set<ADVBinaryTreeNode<?>> visitedNodes) {
        if (childNodeInformation.getChildNode() != null) {
            logger.debug("Child-Node: " + childNodeInformation.getChildNode()
                    .toString());
            final long leftChildRank = 2 * childNodeInformation.getChildRank();
            final long rightChildRank =
                    2 * childNodeInformation.getChildRank() + 1;

            checkCyclicNode(visitedNodes,
                    childNodeInformation.getParentRank(),
                    childNodeInformation.getChildNode());

            addNodeToArray(childNodeInformation.getChildNode(), nodeArray,
                    binaryTreeModule,
                    (int) childNodeInformation.getChildRank());

            addNodeToModuleGroup(moduleGroup, childNodeInformation);

            buildNode(moduleGroup,
                    new NodeInformationHolder(
                            childNodeInformation.getChildRank(),
                            leftChildRank,
                            childNodeInformation.getChildNode().getLeftChild()),
                    binaryTreeModule, nodeArray, visitedNodes);

            buildNode(moduleGroup,
                    new NodeInformationHolder(
                            childNodeInformation.getChildRank(),
                            rightChildRank,
                           childNodeInformation.getChildNode().getRightChild()),
                    binaryTreeModule, nodeArray, visitedNodes);
        }
    }

    private void addNodeToModuleGroup(ModuleGroup moduleGroup,
                                      NodeInformationHolder nodeInformation) {
        moduleGroup.addElement(new TreeNodeElement(
                nodeInformation.getChildNode(),
                nodeInformation.getChildRank()));

        moduleGroup.addRelation(new TreeNodeRelation(
                nodeInformation.getParentRank(),
                nodeInformation.getChildRank(),
                nodeInformation.getChildNode().getStyle()));
    }

    private void checkCyclicNode(Set<ADVBinaryTreeNode<?>> visitedNodes,
                                 long parentRank,
                                 ADVBinaryTreeNode<?> childNode) {
        if (visitedNodes.contains(childNode)) {
            String errorMessage = "the child (" + childNode.toString()
                    + " of Parent with Rank " + parentRank + "is already a "
                    + "node in the tree";
            logger.error(errorMessage);
            throw new CyclicNodeException(errorMessage);
        } else {
            visitedNodes.add(childNode);
        }
    }
}
