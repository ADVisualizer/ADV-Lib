package ch.adv.lib.stack.logic;

import ch.adv.lib.core.logic.ADVModule;
import ch.adv.lib.core.logic.domain.ADVRelation;
import ch.adv.lib.stack.logic.domain.Coordinate;

import java.util.List;
import java.util.Map;

/**
 * Encapsulates business logic classes needed to to transform stack data into
 * json to be sent to the ADVCore UI. It is not recommended to override the
 * default methods!
 *
 * @param <T> depends on the content of the stack
 */
public interface StackModule<T> extends ADVModule {
    /**
     * @return the stack data to be displayed
     */
    T[] getStack();

    @Override
    default String getModuleName() {
        return "stack";
    }

    /**
     * @return optional fixed parameteres for the stack elements. If set, the
     * placement will not be calculated by the ADVCore UI.
     */
    Map<Integer, Coordinate> getCoordinates();

    /**
     * @return optional relations between two ADVelements
     */
    List<ADVRelation> getRelations();

    /**
     * Display objects as independent objects
     *
     * @return whether to show the independent objects or not
     */
    boolean showObjectRelations();

}
