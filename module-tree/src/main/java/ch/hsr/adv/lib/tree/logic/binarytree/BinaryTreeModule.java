package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeModuleBase {

    private final TreeHeightHolder maxTreeHeights;

    private ADVBinaryTreeNode<?> root;
    private boolean showArray;

    public BinaryTreeModule(String sessionName) {
        this(null, sessionName);
    }

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
        showArray = false;
        maxTreeHeights = new TreeHeightHolder();
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

    public boolean isShowArray() {
        return showArray;
    }

    public void setShowArray(boolean showArray) {
        this.showArray = showArray;
    }

    public TreeHeightHolder getMaxTreeHeights() {
        return maxTreeHeights;
    }

    /**
     * In case the array should not be visible in the UI, the BinaryTreeBuilder
     * has the possibility to remove the ArrayModule
     */
    void removeArrayModule() {
        if (getChildModules().size() > 0 && getChildModules().get(0)
                instanceof ArrayModule) {
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
        arrayModule.setShowArrayIndices(true);
        if (getChildModules().size() > 0 && getChildModules().get(0)
                instanceof ArrayModule) {
            getChildModules().set(0, arrayModule);
        } else {
            getChildModules().add(0, arrayModule);
        }
    }

    /**
     * In case the client wants to display the nodes in the ui at fixed
     * positions he must set the maximum left height and maximum right height
     * of a tree instead of just the height of the tree. This is needed because
     * of the two special cases (left hanging and right hanging tree) and the
     * fact that the module doesn't know the final tree until the last snapshot
     * was made by the client. If the tree height exceeds the greater value of
     * the parameters then the tree won't display properly.
     *
     * @param maxLeftHeight the maximum height of the tree on the left side
     * @param maxRightHeight the maximum height of the tree on the right side
     */
    public void setFixedTreeHeight(int maxLeftHeight, int maxRightHeight) {
        maxTreeHeights.setMaxLeftHeight(maxLeftHeight);
        maxTreeHeights.setMaxRightHeight(maxRightHeight);
    }

    /**
     * In case the client wants to release the fixed heights for the next
     * snapshot he can clear the values
     */
    public void clearFixedTreeHeight() {
        maxTreeHeights.clearValues();
    }
}
