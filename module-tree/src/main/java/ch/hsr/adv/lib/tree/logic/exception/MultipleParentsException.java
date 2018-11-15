package ch.hsr.adv.lib.tree.logic.exception;

/**
 * An exception used when a tree-node has multiple parents
 */
public class MultipleParentsException extends RuntimeException {
    public MultipleParentsException(String message) {
        super(message);
    }
}
