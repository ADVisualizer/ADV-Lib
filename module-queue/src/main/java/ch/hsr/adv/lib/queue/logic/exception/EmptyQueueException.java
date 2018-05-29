/*
 * HSR - Uebungen 'Algorithmen & Datenstrukturen 1'
 * Version: Mon Apr  9 16:30:09 CEST 2018
 */

package ch.hsr.adv.lib.queue.logic.exception;

/**
 * Runtime exception thrown when one tries to perform operation top or pop on an
 * empty stack.
 */

public class EmptyQueueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmptyQueueException(String err) {
        super(err);
    }
}
