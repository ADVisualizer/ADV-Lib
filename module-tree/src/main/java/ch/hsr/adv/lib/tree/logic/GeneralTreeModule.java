package ch.hsr.adv.lib.tree.logic;

import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;

/**
 * Encapsulates module meta data and GeneralNode data to be sent to the ADV UI.
 */
public class GeneralTreeModule extends TreeModuleBase {

    private final ADVGeneralTreeNode<?> root;

    public GeneralTreeModule(ADVGeneralTreeNode<?> root, String sessionName) {
        super(sessionName);
        this.root = root;
    }

    public ADVGeneralTreeNode<?> getRoot() {
        return root;
    }

    @Override
    public String getModuleName() {
        return ConstantsTree.MODULE_NAME_GENERAL_TREE;
    }
}
