package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.domain.ADVTreeNode;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeElement;
import ch.hsr.adv.commons.tree.logic.domain.TreeNodeRelation;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;


/**
 * A base class for the NodeTreeBuilder classes to encapsulate shared
 * functionality
 */
public abstract class TreeBuilderBase {

    protected static final long DEFAULT_PARENT_ID = -1;

    /**
     * adds tree-node and its parent-child-relation to the ModuleGroup
     *
     * @param moduleGroup     module that receives the node
     * @param nodeInformation information about the node
     */
    protected void addNodeToModuleGroup(
            ModuleGroup moduleGroup,
            NodeInformationHolder<? extends ADVTreeNode<?>> nodeInformation) {
        moduleGroup.addElement(new TreeNodeElement(
                nodeInformation.getChildNode(),
                nodeInformation.getChildRank()));

        if (nodeInformation.getParentRank() != DEFAULT_PARENT_ID) {
            moduleGroup.addRelation(new TreeNodeRelation(
                    nodeInformation.getParentRank(),
                    nodeInformation.getChildRank(),
                    nodeInformation.getChildNode().getStyle()));
        }
    }
}
