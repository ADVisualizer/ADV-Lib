package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeBinaryModuleBase;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeBinaryModuleBase {

    private ADVBinaryTreeNode<?> root;

    public BinaryTreeModule(String sessionName) {
        this(null, sessionName);
    }

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
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

    protected TreeHeightHolder getMaxTreeHeights() {
        return super.getMaxTreeHeights();
    }

    /**
     * this method is used only by the builder and should not be publicly
     * available
     */
    protected void removeArrayModule() {
        super.removeArrayModule();
    }

    /**
     * method used by the builder to attach the nodeArray to the ArrayModule
     * child module in order to display it in the ui
     *
     * @param nodeArray array to append
     */
    void appendArrayToModule(String[] nodeArray) {
        ArrayModule arrayModule = new ArrayModule(nodeArray);
        super.appendArrayToModule(arrayModule);
    }

}
