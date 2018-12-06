package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * this module class is intended to be used only by the lecturer and not the
 * students because it offers convenience features that lowers learning effects
 * of the students if they used them
 *
 * @param <T> type of the array content
 */
public class BinaryArrayTreeEnhancedModule<T> extends BinaryArrayTreeModule<T> {
    public BinaryArrayTreeEnhancedModule(T[] nodeArray, String sessionName) {
        super(nodeArray, sessionName);
    }

    public BinaryArrayTreeEnhancedModule(ArrayList<T> nodeList,
                                         String sessionName) {
        super(nodeList, sessionName);
    }


    /**
     * Method to add a value at a left child node position of a given parent
     * index. IMPORTANT: it does not add the root node (because it is not a
     * child of a node)
     *
     * @param parentIndex represents the rank of the parent
     * @param content     value to store
     */
    public void addLeftChild(int parentIndex, T content) {
        addChild(parentIndex, 2 * parentIndex, content);
    }

    /**
     * Method to add a value at a right child node position of a given parent
     * index. IMPORTANT: it does not add the root node (because it is not a
     * child of a node)
     *
     * @param parentIndex represents the rank of the parent
     * @param content     value to store
     */
    public void addRightChild(int parentIndex, T content) {
        addChild(parentIndex, 2 * parentIndex + 1, content);
    }

    private void addChild(int parentIndex, int childRank, T content) {
        if (parentIndex < 1 || parentIndex >= moduleNodeArray.length) {
            throw new IllegalArgumentException("the parent element index must"
                    + " be greater than 0 and contained in the array; the "
                    + "given index was: " + parentIndex);
        }
        if (moduleNodeArray[parentIndex] == null) {
            throw new IllegalArgumentException("The parent element must not "
                    + "be null");
        }

        if (childRank >= moduleNodeArray.length) {
            moduleNodeArray = Arrays.copyOf(moduleNodeArray,
                    moduleNodeArray.length * 2);
        }

        moduleNodeArray[childRank] = content;
    }
}
