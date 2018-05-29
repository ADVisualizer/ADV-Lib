package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.commons.core.logic.domain.Module;
import ch.hsr.adv.commons.core.logic.domain.ModuleGroup;
import ch.hsr.adv.commons.graph.logic.ConstantsGraph;
import ch.hsr.adv.commons.graph.logic.domain.ADVEdge;
import ch.hsr.adv.commons.graph.logic.domain.ADVVertex;
import ch.hsr.adv.commons.graph.logic.domain.GraphElement;
import ch.hsr.adv.commons.graph.logic.domain.GraphRelation;
import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.graph.logic.domain.*;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Builder Implementation for graph module. It builds a ModuleGroup
 * containing the module data. Can only handle graph module!
 */
@Singleton
@Module(ConstantsGraph.MODULE_NAME)
public class GraphBuilder implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(
            GraphBuilder.class);

    @Override
    public ModuleGroup build(ADVModule advModule) {
        if (ConstantsGraph.MODULE_NAME.equals(advModule.getModuleName())) {
            logger.info("Building graph session...");
            GraphModule module = (GraphModule) advModule;

            ModuleGroup moduleGroup = new ModuleGroup(
                    advModule.getModuleName());

            buildVertices(moduleGroup, module.getGraph().getVertices());
            buildRelations(moduleGroup, module.getGraph().getEdges());

            return moduleGroup;
        } else {
            return null;
        }
    }

    private void buildRelations(ModuleGroup moduleGroup,
                                Collection<? extends ADVEdge> edges) {
        edges.forEach(edge -> {
            GraphRelation relation = new GraphRelation(edge);
            moduleGroup.addRelation(relation);
        });
    }

    private void buildVertices(ModuleGroup moduleGroup,
                               Collection<? extends ADVVertex> vertices) {
        vertices.forEach(vertex -> {
            GraphElement element = new GraphElement(vertex);
            moduleGroup.addElement(element);
        });
    }
}
