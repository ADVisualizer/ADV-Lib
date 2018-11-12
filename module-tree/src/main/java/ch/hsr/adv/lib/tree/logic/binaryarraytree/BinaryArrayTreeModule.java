package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Encapsulates module meta data and a node array to be sent to the ADV UI.
 *
 * @param <T> type of the array elements
 */
public class BinaryArrayTreeModule<T> extends TreeModuleBase {

    private boolean showArray;
    private T[] moduleNodeArray;

    public BinaryArrayTreeModule(T[] nodeArray, String sessionName) {
        super(sessionName);
        initialize(nodeArray);
    }

    public BinaryArrayTreeModule(ArrayList<T> nodeList, String sessionName) {
        super(sessionName);
        initialize(convertToArray(nodeList));
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_ARRAY_TREE;
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

    private boolean hasRoot(T[] nodeArray) {
        return nodeArray.length >= 2 && nodeArray[1] != null;
    }

    private boolean hasProperLength(T[] nodeArray) {
        int calculatedHeight = getCalculatedTreeHeight(nodeArray);

        return (int) Math.pow(2, calculatedHeight + 1) == nodeArray.length;
    }

    private int getCalculatedTreeHeight(T[] nodeArray) {
        return (int) (Math.log(nodeArray.length) / Math.log(2)) - 1;
    }

    private void initialize(T[] nodeArray) {
        showArray = false;
        setArray(nodeArray);
    }

    @SuppressWarnings("unchecked")
    private T[] convertToArray(ArrayList<T> nodeList) {
        return (T[]) nodeList.toArray();
    }

    public boolean isShowArray() {
        return showArray;
    }

    public void setShowArray(boolean showArray) {
        this.showArray = showArray;
    }

    T[] getModuleNodeArray() {
        return Arrays.copyOf(moduleNodeArray, moduleNodeArray.length);
    }

    /**
     * method used by the builder to attach the nodeArray to the ArrayModule
     * child module in order to display it in the ui
     */
    public void appendArrayToModule() {
        ArrayModule arrayModule = new ArrayModule(moduleNodeArray);
        if (getChildModules().size() > 0 && getChildModules().get(0)
                instanceof ArrayModule) {
            getChildModules().set(0, arrayModule);
        } else {
            getChildModules().add(0, arrayModule);
        }
    }

    /**
     * Method to change the array in case that the tree height changes when
     * new nodes are added to the array
     *
     * @param nodeList the node array
     */
    public void setArray(ArrayList<T> nodeList) {
        setArray(convertToArray(nodeList));
    }

    /**
     * Method to change the array in case that the tree height changes when
     * new nodes are added to the array
     *
     * @param nodeArray the node array
     */
    public void setArray(T[] nodeArray) {
        if (!hasRoot(nodeArray)) {
            throw new RootUnspecifiedException("Root should not be null");
        }

        if (!hasProperLength(nodeArray)) {
            throw new IllegalArgumentException("The array size should be "
                    + "calculated like 2^height + 1, but actual was: "
                    + nodeArray.length);
        }

        this.moduleNodeArray = Arrays.copyOf(nodeArray, nodeArray.length);
    }
}
