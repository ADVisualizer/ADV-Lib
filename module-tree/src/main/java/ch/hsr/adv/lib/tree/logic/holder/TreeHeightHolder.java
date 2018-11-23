package ch.hsr.adv.lib.tree.logic.holder;

/**
 * TreeHeightHolder contains the maximum left and right tree height used by
 * the module to store the information in case the client wants fixed nodes
 */
public class TreeHeightHolder {
    private static final int UNSET_HEIGHT = -1;

    private int leftHeight;
    private int rightHeight;

    public TreeHeightHolder() {
        leftHeight = UNSET_HEIGHT;
        rightHeight = UNSET_HEIGHT;
    }

    public void setLeftHeight(int leftHeight) {
        this.leftHeight = leftHeight;
    }

    public void setRightHeight(int rightHeight) {
        this.rightHeight = rightHeight;
    }

    public int getLeftHeight() {
        return leftHeight;
    }

    public int getRightHeight() {
        return rightHeight;
    }

    public boolean isSet() {
        return leftHeight != -1 || rightHeight != -1;
    }

    /**
     * used by the module to clear the heights
     */
    public void clearValues() {
        leftHeight = UNSET_HEIGHT;
        rightHeight = UNSET_HEIGHT;
    }
}
