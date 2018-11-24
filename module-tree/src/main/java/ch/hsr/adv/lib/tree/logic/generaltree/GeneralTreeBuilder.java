package ch.hsr.adv.lib.tree.logic.generaltree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.TreeBuilderBase;
import ch.hsr.adv.lib.tree.logic.exception.RootUnspecifiedException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import ch.hsr.adv.lib.tree.logic.util.NodeTreeUtility;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder Strategy for GeneralTreeModule. It builds a ModuleGroup
 * containing the module data. Can handle trees with n-children
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_GENERAL_TREE)
public class GeneralTreeBuilder extends TreeBuilderBase implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            GeneralTreeBuilder.class);

    private static final long START_ID = 1;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_GENERAL_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building Modulegroup from GeneralTreeModule");

            GeneralTreeModule module = (GeneralTreeModule) advModule;
            ADVGeneralTreeNode<?> root = module.getRoot();
            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());

            if (root != null) {
                buildNodes(root, moduleGroup);
            } else {
                logger.error("Root Node from the GeneralTreeModule is null");
                throw new RootUnspecifiedException("The root node must not be"
                        + " null");
            }

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildNodes(ADVGeneralTreeNode<?> root,
                            ModuleGroup moduleGroup) {
        logger.debug("Root Node: " + root.toString());

        NodeTreeUtility.buildTree(root,
                START_ID, moduleGroup, this::buildNode);
    }

    private void buildNode(ModuleGroup moduleGroup, ADVGeneralTreeNode<?> node,
                           long parentId, long childId) {
        if (node != null) {
            logger.debug("ChildNode: " + node.getContent());

            addNodeToModuleGroup(moduleGroup,
                    new NodeInformationHolder<>(parentId, childId, node));
        } else {
            logger.error("The childNode with ID "
                    + childId + " and parentID "
                    + parentId + " is null");
            throw new IllegalArgumentException("The childNode with ID "
                    + childId + " and parentID "
                    + parentId + " must not be "
                    + "null!");
        }
    }
}
