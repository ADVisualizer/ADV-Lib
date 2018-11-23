package ch.hsr.adv.lib.tree.logic.binarytree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.array.logic.ArrayModule;
import ch.hsr.adv.lib.tree.logic.TreeBinaryModuleBase;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule extends TreeBinaryModuleBase {

    private ADVBinaryTreeNode<?> root;

    public BinaryTreeModule(String sessionName) {
        this(null, sessionName);
    }

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_TREE;
    }

    public ADVBinaryTreeNode<?> getRoot() {
        return root;
    }

    public void setRoot(ADVBinaryTreeNode<?> newRoot) {
        root = newRoot;
    }

    /**
     * this method is used only by the builder and should not be publicly
     * available
     */
    protected void removeArrayModule() {
        super.removeArrayModule();
    }

    /**
     * method used by the builder to attach the nodeArray to the ArrayModule
     * child module in order to display it in the ui
     *
     * @param nodeArray array to append
     */
    void appendArrayToModule(String[] nodeArray) {
        ArrayModule arrayModule = new ArrayModule(nodeArray);
        arrayModule.setShowArrayIndices(true);
        if (getChildModules().size() > 0 && getChildModules().get(0)
                instanceof ArrayModule) {
            getChildModules().set(0, arrayModule);
        } else {
            getChildModules().add(0, arrayModule);
        }
    }

}
