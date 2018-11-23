package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;

/**
 * abstract base class for all modules responsible for binary trees used to
 * encapsulate the same parts
 */
public abstract class TreeBinaryModuleBase extends TreeModuleBase {


    private final TreeHeightHolder maxTreeHeights;
    private boolean showArray;

    protected TreeBinaryModuleBase(String sessionName) {
        super(sessionName);
        maxTreeHeights = new TreeHeightHolder();
        showArray = false;
    }

    public boolean isShowArray() {
        return showArray;
    }

    public void setShowArray(boolean showArray) {
        this.showArray = showArray;
    }

    protected TreeHeightHolder getMaxTreeHeights() {
        return maxTreeHeights;
    }

    /**
     * In case the array should not be visible in the UI, the BinaryTreeBuilder
     * has the possibility to remove the ArrayModule
     */
    protected void removeArrayModule() {
        if (getChildModules().size() > 0 && getChildModules().get(0)
                instanceof ArrayModule) {
            getChildModules().remove(0);
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
        maxTreeHeights.setLeftHeight(maxLeftHeight);
        maxTreeHeights.setRightHeight(maxRightHeight);
    }

    /**
     * In case the client wants to release the fixed heights for the next
     * snapshot he can clear the values
     */
    public void clearFixedTreeHeight() {
        maxTreeHeights.clearValues();
    }
}
