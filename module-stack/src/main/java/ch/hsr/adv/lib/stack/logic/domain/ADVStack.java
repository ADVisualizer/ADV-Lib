package ch.hsr.adv.lib.stack.logic.domain;

import ch.hsr.adv.lib.stack.logic.exceptions.EmptyStackException;

/**
 * Stack interface.
 *
 * @param <T> type
 */
public interface ADVStack<T> {

    /**
     * Returns the number of elements in the stack.
     *
     * @return Number of elements in the stack.
     */
    int size();

    /**
     * Returns whether the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Inspects the element at the top of the stack.
     *
     * @return Top element in the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    T top() throws EmptyStackException;

    /**
     * Inserts an element at the top of the stack.
     *
     * @param element Element to be inserted.
     */
    void push(T element);

    /**
     * Removes the top element from the stack.
     *
     * @return Element removed.
     * @throws EmptyStackException If the stack is empty.
     */
    T pop() throws EmptyStackException;

}
