package ch.hsr.adv.lib.tree.logic.util;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.tree.logic.exception.NodeFixationException;

/**
 * class with utility methods for the binary builder classes to encapsulate
 * similar code
 */
public final class BinaryBuilderUtility {

    /**
     * set the metadata to the moduleGroup for the ui that it can show the nodes
     * with fixed positions
     *
     * @param moduleGroup    data sent to the ui
     * @param treeHeight     height of the tree
     * @param maxLeftHeight  defined maximum left height from the client
     * @param maxRightHeight defined maximum right height from the client
     */
    public static void appendMaxTreeHeights(ModuleGroup moduleGroup,
                                            int treeHeight,
                                            int maxLeftHeight,
                                            int maxRightHeight) {
        if (treeHeight <= Math.max(maxLeftHeight, maxRightHeight)) {
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_LEFT,
                            String.valueOf(maxLeftHeight));
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_RIGHT,
                            String.valueOf(maxRightHeight));
        } else {
            String errorMessage = "The maximum tree height ("
                    + treeHeight + ") exceeds the set maximum left ("
                    + maxLeftHeight + ") or right tree height ("
                    + maxRightHeight
                    + "). You must set the correct maximum left or right tree"
                    + " height to actual tree height";
            throw new NodeFixationException(errorMessage);

        }
    }
}
