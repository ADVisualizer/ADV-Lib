package ch.hsr.adv.lib.queue.logic.domain;

import ch.hsr.adv.lib.queue.logic.exception.EmptyQueueException;

/**
 * Queue interface.
 *
 * @param <T> type
 */
public interface ADVQueue<T> {
    /**
     * Returns the number of elements in the queue
     *
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();


    /**
     * Inspects the element at the front of the queue
     *
     * @return the front element in the queue
     * @throws EmptyQueueException If the queue is empty
     */
    T min() throws EmptyQueueException;

    /**
     * Inserts an element into the queue
     *
     * @param element Element to be inserted.
     */
    void insert(T element);

    /**
     * Removes the front element from the queue
     *
     * @return removed element
     * @throws EmptyQueueException If the queue is empty
     */
    T removeMin() throws EmptyQueueException;

}
