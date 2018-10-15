package ch.hsr.adv.lib.tree.logic.exception;

/**
 * Exception in case the root node is null. It is used because of further
 * method calls like getContent which require the root node not to be null
 */
public class RootUnspecifiedException extends RuntimeException {
    public RootUnspecifiedException(String message) {
        super(message);
    }
}
