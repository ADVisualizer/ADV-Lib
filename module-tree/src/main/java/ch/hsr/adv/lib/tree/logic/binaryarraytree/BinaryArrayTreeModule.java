package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;

import java.util.ArrayList;

/**
 * Encapsulates module meta data and a node array to be sent to the ADV UI.
 *
 * @param <T> type of the array elements
 */
public class BinaryArrayTreeModule<T> extends TreeModuleBase {

    public BinaryArrayTreeModule(T[] nodeArray, String sessionName) {
        super(sessionName);
        setArray(nodeArray);
    }

    @SuppressWarnings("unchecked")
    public BinaryArrayTreeModule(ArrayList<T> nodeList, String sessionName) {
        super(sessionName);

        T[] nodeArray = (T[]) nodeList.toArray();
        setArray(nodeArray);
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_ARRAY_TREE;
    }

    /**
     * Adds child-Modules, which is important for the Array Visualization of
     * a binary tree which needs the array-module
     *
     * @param module child module
     */
    public void addChildModule(ADVModule module) {
        this.getChildModules().add(module);
    }

    /**
     * In case the array should not be visible in the UI, the BinaryTreeBuilder
     * has the possibility to remove the ArrayModule
     */
    void removeArrayModule() {
        if (getChildModules().size() > 0) {
            getChildModules().remove(0);
        }
    }

    private boolean hasRoot(T[] nodeArray) {
        return nodeArray.length >= 2 && nodeArray[1] != null;
    }

    private void setArray(T[] nodeArray) {
        if (!hasRoot(nodeArray)) {
            throw new RootUnspecifiedException("Root should not be null");
        }

        if (!hasProperLength(nodeArray)) {
            throw new IllegalArgumentException("The array size should be "
                    + "calculated like 2^height + 1, but actual was: "
                    + nodeArray.length);
        }

        addChildModule(new ArrayModule(nodeArray));
    }

    private boolean hasProperLength(T[] nodeArray) {
        int calculatedHeight = getCalculatedTreeHeight(nodeArray);

        return (int) Math.pow(2, calculatedHeight + 1) == nodeArray.length;
    }

    private int getCalculatedTreeHeight(T[] nodeArray) {
        return (int) (Math.log(nodeArray.length) / Math.log(2)) - 1;
    }
}
