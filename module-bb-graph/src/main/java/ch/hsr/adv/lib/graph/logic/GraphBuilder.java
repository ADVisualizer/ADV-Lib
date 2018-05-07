package ch.hsr.adv.lib.graph.logic;

import ch.hsr.adv.lib.core.logic.ADVModule;
import ch.hsr.adv.lib.core.logic.Builder;
import ch.hsr.adv.lib.core.logic.domain.Module;
import ch.hsr.adv.lib.core.logic.domain.ModuleGroup;
import ch.hsr.adv.lib.core.logic.domain.Session;
import ch.hsr.adv.lib.core.logic.domain.Snapshot;
import ch.hsr.adv.lib.graph.logic.domain.ADVEdge;
import ch.hsr.adv.lib.graph.logic.domain.ADVVertex;
import ch.hsr.adv.lib.graph.logic.domain.GraphElement;
import ch.hsr.adv.lib.graph.logic.domain.GraphRelation;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder Implementation for graph module. It builds a whole session with a
 * snapshot. Can only handle graph module!
 *
 * @param <V> type of the vertex value
 * @param <E> type of the edge value
 */
@Singleton
@Module("graph")
public class GraphBuilder<V, E> implements Builder {
    private static final Logger logger = LoggerFactory.getLogger(
            GraphBuilder.class);
    private ModuleGroup moduleGroup = new ModuleGroup();
    private Map<ADVVertex<V>, GraphElement> elements = new HashMap<>();

    @Override
    public ModuleGroup build(ADVModule advModule) {
        logger.info("Building graph session...");
        GraphModule<V, E> module = (GraphModule<V, E>) advModule;

        buildElements(module.getGraph().getVertices());
        buildRelations(module.getGraph().getEdges());
        moduleGroup.setModuleName("graph");
        return moduleGroup;
    }

    private void buildRelations(Collection<ADVEdge<E>> edges) {
        edges.forEach(edge -> {
            long sourceId = elements.get(edge.getStarVertex()).getId();
            long targetId = elements.get(edge.getEndVertex()).getId();
            GraphRelation relation = new GraphRelation(edge, sourceId,
                    targetId);
            moduleGroup.addRelation(relation);
        });
    }

    private void buildElements(Collection<ADVVertex<V>> vertices) {
        vertices.forEach(vertex -> {
            GraphElement element = new GraphElement(vertex);
            elements.put(vertex, element);
            moduleGroup.addElement(element);
        });
    }

}
