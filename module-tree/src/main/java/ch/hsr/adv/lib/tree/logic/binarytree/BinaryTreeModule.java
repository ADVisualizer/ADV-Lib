package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeModuleBase {

    private ADVBinaryTreeNode<?> root;
    private boolean showArray;

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
        showArray = false;
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_TREE;
    }

    public ADVBinaryTreeNode<?> getRoot() {
        return root;
    }

    public void setRoot(ADVBinaryTreeNode<?> newRoot) {
        root = newRoot;
    }

    /**
     * Adds child-Modules, which is important for the Array Visualization of
     * a binary tree which needs the array-module
     *
     * @param module child module
     */
    public void addChildModule(ADVModule module) {
        getChildModules().add(module);
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
            getChildModules().remove(0);
        }
    }

    /**
     * method used by the builder to attach the nodeArray to the ArrayModule
     * child module in order to display it in the ui
     *
     * @param nodeArray array to append
     */
    void appendArrayToModule(String[] nodeArray) {
        ArrayModule arrayModule = new ArrayModule(nodeArray);
        getChildModules().add(0, arrayModule);
    }
}
