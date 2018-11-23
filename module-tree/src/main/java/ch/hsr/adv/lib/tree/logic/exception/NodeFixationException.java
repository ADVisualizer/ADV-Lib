package ch.hsr.adv.lib.tree.logic.exception;

/**
 * Exception in case something fails when the tree left and right height is
 * not set properly while building the module
 */
public class NodeFixationException extends RuntimeException {
    public NodeFixationException(String message) {
        super(message);
    }
}
