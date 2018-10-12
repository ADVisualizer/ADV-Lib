package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVBinaryTreeNode;
import ch.hsr.adv.lib.core.logic.ADVModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates module meta data and BinaryNode data to be sent to the ADV UI.
 */
public class BinaryTreeModule implements ADVModule {

    private final String sessionName;
    private final List<ADVModule> childModules = new ArrayList<>();

    private final ADVBinaryTreeNode<?> root;

    public BinaryTreeModule(ADVBinaryTreeNode<?> root, String sessionName) {
        this.root = root;
        this.sessionName = sessionName;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_BINARY_TREE;
    }

    @Override
    public List<ADVModule> getChildModules() {
        return childModules;
    }

    public ADVBinaryTreeNode<?> getRoot() {
        return root;
    }

    /**
     * Adds child-Modules, which is important for the Array Visualization of
     * a binary tree which needs the array-module
     * @param module child module
     */
    public void addChildModule(ADVModule module) {
        this.childModules.add(module);
    }
}
