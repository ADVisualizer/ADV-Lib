package ch.hsr.adv.lib.tree.logic.generaltree;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.tree.logic.TreeModuleBase;

/**
 * Encapsulates module meta data and GeneralNode data to be sent to the ADV UI.
 */
public class GeneralTreeModule extends TreeModuleBase {

    private ADVGeneralTreeNode<?> root;

    public GeneralTreeModule(String sessionName) {
        this(null, sessionName);
    }

    public GeneralTreeModule(ADVGeneralTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
    }

    public ADVGeneralTreeNode<?> getRoot() {
        return root;
    }

    public void setRoot(ADVGeneralTreeNode<?> newRoot) {
        root = newRoot;
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_GENERAL_TREE;
    }
}
