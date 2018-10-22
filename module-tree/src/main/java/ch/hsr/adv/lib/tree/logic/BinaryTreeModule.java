package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeModuleBase {

    private final ADVBinaryTreeNode<?> root;
    private boolean showArrayIndices;

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
        showArrayIndices = false;

        addChildModule(new ArrayModule(new String[0]));
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_TREE;
    }

    public ADVBinaryTreeNode<?> getRoot() {
        return root;
    }

    /**
     * Adds child-Modules, which is important for the Array Visualization of
     * a binary tree which needs the array-module
     * @param module child module
     */
    public void addChildModule(ADVModule module) {
        this.getChildModules().add(module);
    }

    public boolean isShowArrayIndices() {
        return showArrayIndices;
    }

    public void setShowArrayIndices(boolean showArrayIndices) {
        this.showArrayIndices = showArrayIndices;
    }
}
