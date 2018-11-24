package ch.hsr.adv.lib.tree.logic.collectiontree;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.tree.logic.ConstantsTree;
import ch.hsr.adv.commons.tree.logic.domain.ADVGeneralTreeNode;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.tree.logic.TreeBuilderBase;
import ch.hsr.adv.lib.tree.logic.exception.CyclicNodeException;
import ch.hsr.adv.lib.tree.logic.exception.MultipleParentsException;
import ch.hsr.adv.lib.tree.logic.holder.NodeInformationHolder;
import ch.hsr.adv.lib.tree.logic.util.NodeTreeUtility;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Builder Strategy for CollectionTreeModule. It builds a ModuleGroup
 * containing the module data. Can only handle collection trees!
 */
@Singleton
@Module(ConstantsTree.MODULE_NAME_COLLECTION_TREE)
public class CollectionTreeBuilder extends TreeBuilderBase implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            CollectionTreeBuilder.class);
    private static final long START_ID = 1;

    private Set<? extends ADVGeneralTreeNode<?>> moduleNodes;
    private List<NodeInformationHolder<? extends ADVGeneralTreeNode<?>>>
            nodeInformationList;

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsTree.MODULE_NAME_COLLECTION_TREE
                .equals(advModule.getModuleName())) {
            logger.info("start building ModuleGroup from CollectionTreeModule");
            CollectionTreeModule<?> module =
                    (CollectionTreeModule<?>) advModule;
            initializeBuild(module);

            ModuleGroup moduleGroup = new ModuleGroup(module.getModuleName());

            List<ADVGeneralTreeNode<?>> roots = findRoots();

            long currentId = START_ID;

            for (ADVGeneralTreeNode<?> root : roots) {
                currentId = NodeTreeUtility.buildTree(root, currentId,
                        moduleGroup,
                        (child) -> moduleNodes.contains(child),
                        this::buildNode);
            }

            if (moduleNodes.size() != nodeInformationList.size()) {
                String errorMessage = "Not all roots correctly "
                        + "identified. Please check for cycles";
                logger.error(errorMessage);
                throw new CyclicNodeException(errorMessage);
            }

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void initializeBuild(CollectionTreeModule<?> module) {
        nodeInformationList = new ArrayList<>();
        moduleNodes = module.getNodes();
    }

    private List<ADVGeneralTreeNode<?>> findRoots() {
        Set<ADVGeneralTreeNode<?>> children = new HashSet<>();
        for (ADVGeneralTreeNode<?> moduleNode : moduleNodes) {
            if (moduleNode.getChildren() != null) {
                for (ADVGeneralTreeNode<?> child : moduleNode.getChildren()) {
                    if (moduleNodes.contains(child)) {
                        if (!children.add(child)) {
                            String errorMessage = "Node (" + child.getContent()
                                    .toString() + ") has multiple parents";
                            logger.error(errorMessage);
                            throw new MultipleParentsException(errorMessage);
                        }
                    }
                }
            }
        }
        List<ADVGeneralTreeNode<?>> roots = new ArrayList<>();
        for (ADVGeneralTreeNode<?> moduleNode : moduleNodes) {
            if (!children.contains(moduleNode)) {
                roots.add(moduleNode);
            }
        }
        return roots;
    }

    private void buildNode(ModuleGroup moduleGroup, ADVGeneralTreeNode<?> node,
                           long parentId, long childId) {
        var nodeInformation = new NodeInformationHolder<>(parentId,
                childId, node);
        nodeInformationList.add(nodeInformation);
        addNodeToModuleGroup(moduleGroup, nodeInformation);
    }
}
