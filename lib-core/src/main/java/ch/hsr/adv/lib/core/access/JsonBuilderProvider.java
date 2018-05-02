package ch.hsr.adv.lib.core.access;

/**
 * Provide Minifier and Prettifyer to serialize json
 *
 * @param <T> the type of the json builder
 */
public interface JsonBuilderProvider<T> {
    /**
     * @return a json builder which builds concise json.
     */
    T getMinifier();

    /**
     * @return a json builder which builds a pretty, human readyble json.
     */
    T getPrettifyer();
}
