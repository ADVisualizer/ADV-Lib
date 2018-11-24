package ch.hsr.adv.lib.tree.logic.binaryarraytree;

import ch.hsr.adv.commons.core.logic.domain.styles.ADVStyle;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeBinaryModuleBase;
import ch.hsr.adv.lib.tree.logic.holder.TreeHeightHolder;

import java.util.*;

/**
 * Encapsulates module meta data and a node array to be sent to the ADV UI.
 *
 * @param <T> type of the array elements
 */
public class BinaryArrayTreeModule<T> extends TreeBinaryModuleBase {

    private T[] moduleNodeArray;
    private Map<Integer, ADVStyle> styles;

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
     * this method is used only by the builder and should not be publicly
     * available
     */
    protected void removeArrayModule() {
        super.removeArrayModule();
    }

    protected TreeHeightHolder getMaxTreeHeights() {
        return super.getMaxTreeHeights();
    }

    private boolean hasProperLength(T[] nodeArray) {
        if (nodeArray.length >= 2) {
            int calculatedHeight = getCalculatedTreeHeight(nodeArray);

            return (int) Math.pow(2, calculatedHeight + 1) == nodeArray.length;
        }
        throw new IllegalArgumentException("The Array size must be at least 2");
    }

    private int getCalculatedTreeHeight(T[] nodeArray) {
        return (int) (Math.log(nodeArray.length) / Math.log(2)) - 1;
    }

    private void initialize(T[] nodeArray) {
        styles = new HashMap<>();
        setArray(nodeArray);
    }

    @SuppressWarnings("unchecked")
    private T[] convertToArray(ArrayList<T> nodeList) {
        if (nodeList != null) {
            return (T[]) nodeList.toArray();
        }
        throw new IllegalArgumentException("The ArrayList must not be null");
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
        arrayModule.getStyleMap().putAll(styles);
        arrayModule.setShowArrayIndices(true);
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
        if (nodeArray == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (!hasProperLength(nodeArray)) {
            throw new IllegalArgumentException("The Array size should be "
                    + "calculated like 2^(height + 1), but actual was: "
                    + nodeArray.length);
        }

        this.moduleNodeArray = Arrays.copyOf(nodeArray, nodeArray.length);
    }

    /**
     * method to attach a style to the node and the array entry on the ui
     *
     * @param rank  identifies which node should get a style
     * @param style the specific style
     */
    public void setStyle(int rank, ADVStyle style) {
        if (rank < 1 || rank >= moduleNodeArray.length) {
            throw new IllegalArgumentException("the rank must be a value "
                    + "between 1 and " + (moduleNodeArray.length - 1) + " but "
                    + "was " + rank);
        }

        styles.put(rank, style);
    }

    public Map<Integer, ADVStyle> getStyles() {
        return Collections.unmodifiableMap(styles);
    }
}
