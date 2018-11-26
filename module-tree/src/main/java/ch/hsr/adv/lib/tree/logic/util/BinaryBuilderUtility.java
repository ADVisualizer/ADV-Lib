package ch.hsr.adv.lib.tree.logic.util;

import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.tree.logic.exception.NodeFixationException;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;
import org.slf4j.Logger;

/**
 * class with utility methods for the binary builder classes to encapsulate
 * similar code
 */
public final class BinaryBuilderUtility {

    /**
     * set the metadata to the moduleGroup for the ui that it can show the nodes
     * with fixed positions
     *  @param moduleGroup      data sent to the ui
     * @param actualTreeHeight height of the tree
     * @param maxTreeHeight    defined maximum height from the client
     * @param logger the logger class from the builder
     */
    public static void appendMaxTreeHeights(ModuleGroup moduleGroup,
                                            TreeHeightHolder actualTreeHeight,
                                            TreeHeightHolder maxTreeHeight,
                                            Logger logger) {
        if (actualTreeHeight.getLeftHeight()
                <= maxTreeHeight.getLeftHeight()
                && actualTreeHeight.getRightHeight()
                <= maxTreeHeight.getRightHeight()) {
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_LEFT,
                            String.valueOf(maxTreeHeight.getLeftHeight()));
            moduleGroup.getMetaData()
                    .put(ConstantsTree.MAX_TREE_HEIGHT_RIGHT,
                            String.valueOf(maxTreeHeight.getRightHeight()));
        } else {
            String errorMessage = "The maximum tree height ("
                    + actualTreeHeight + ") exceeds the set maximum left ("
                    + maxTreeHeight.getLeftHeight() + ") or right tree "
                    + "height ("
                    + maxTreeHeight.getRightHeight()
                    + "). You must set the correct maximum left or right tree"
                    + " height to actual tree height";
            logger.error(errorMessage);
            throw new NodeFixationException(errorMessage);

        }
    }
}
