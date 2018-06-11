/*
 * HSR - Uebungen 'Algorithmen & Datenstrukturen 1'
 * Version: Mon Apr  9 16:30:09 CEST 2018
 */

package ch.hsr.adv.lib.queue.logic.exception;

/**
 * Runtime exception thrown when operation removeMin or min is executed on an
 * empty queue.
 */

public class EmptyQueueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmptyQueueException(String err) {
        super(err);
    }
}
