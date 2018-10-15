package ch.hsr.adv.lib.tree.logic.exception;

/**
 * An exception used when a tree has cyclic dependencies
 */
public class CyclicNodeException extends RuntimeException {
    public CyclicNodeException(String message) {
        super(message);
    }
}
