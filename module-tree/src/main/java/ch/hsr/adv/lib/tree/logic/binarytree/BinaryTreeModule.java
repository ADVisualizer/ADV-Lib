package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeModuleBase {

    private final ADVBinaryTreeNode<?> root;
    private boolean showArray;

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
        showArray = false;

        addChildModule(new ArrayModule(new String[0]));
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_TREE;
    }

    public ADVBinaryTreeNode<?> getRoot() {
        return root;
    }

    public boolean isShowArray() {
        return showArray;
    }

    public void setShowArray(boolean showArray) {
        this.showArray = showArray;
    }

    /**
     * In case the array should not be visible in the UI, the BinaryTreeBuilder
     * has the possibility to remove the ArrayModule
     */
    void removeArrayModule() {
        if (getChildModules().size() > 0) {
            ArrayModule arrayModule = (ArrayModule) getChildModules().get(0);
            arrayModule.setArray(new String[0]);
        }
    }
}
