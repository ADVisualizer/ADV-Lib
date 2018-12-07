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

    /**
     * setter method with boundary check that you can't add illegal heights
     *
     * @param leftHeight leftHeight
     */
    public void setLeftHeight(int leftHeight) {
        if (leftHeight <= UNSET_HEIGHT) {
            throw new IllegalArgumentException("The height must be 0 or "
                    + "greater");
        }
        this.leftHeight = leftHeight;
    }

    /**
     * setter method with boundary check that you can't add illegal heights
     *
     * @param rightHeight leftHeight
     */
    public void setRightHeight(int rightHeight) {
        if (rightHeight <= UNSET_HEIGHT) {
            throw new IllegalArgumentException("The height must be 0 or "
                    + "greater");
        }

        this.rightHeight = rightHeight;
    }

    public int getLeftHeight() {
        return leftHeight;
    }

    public int getRightHeight() {
        return rightHeight;
    }

    public boolean isSet() {
        return leftHeight > UNSET_HEIGHT || rightHeight > UNSET_HEIGHT;
    }

    /**
     * used by the module to clear the heights
     */
    public void clearValues() {
        leftHeight = UNSET_HEIGHT;
        rightHeight = UNSET_HEIGHT;
    }
}
