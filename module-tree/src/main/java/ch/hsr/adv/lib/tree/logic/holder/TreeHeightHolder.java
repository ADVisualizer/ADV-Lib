package ch.hsr.adv.lib.tree.logic.holder;

/**
 * TreeHeightHolder contains the maximum left and right tree height used by
 * the module to store the information in case the client wants fixed nodes
 */
public class TreeHeightHolder {
    private static final int UNSET_HEIGHT = -1;

    private int maxLeftHeight;
    private int maxRightHeight;

    public TreeHeightHolder() {
        maxLeftHeight = UNSET_HEIGHT;
        maxRightHeight = UNSET_HEIGHT;
    }

    public void setMaxLeftHeight(int maxLeftHeight) {
        this.maxLeftHeight = maxLeftHeight;
    }

    public void setMaxRightHeight(int maxRightHeight) {
        this.maxRightHeight = maxRightHeight;
    }

    public int getMaxLeftHeight() {
        return maxLeftHeight;
    }

    public int getMaxRightHeight() {
        return maxRightHeight;
    }

    public boolean isSet() {
        return maxLeftHeight != -1 || maxRightHeight != -1;
    }

    /**
     * used by the module to clear the heights
     */
    public void clearValues() {
        maxLeftHeight = UNSET_HEIGHT;
        maxRightHeight = UNSET_HEIGHT;
    }
}
