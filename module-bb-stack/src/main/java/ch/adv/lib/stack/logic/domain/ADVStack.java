package ch.adv.lib.stack.logic.domain;

public interface ADVStack<T> {

    /**
     * Return the number of elements in the stack.
     *
     * @return Number of elements in the stack.
     */
    int size();

    /**
     * Return whether the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Inspect the element at the top of the stack.
     *
     * @return Top element in the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    T top() throws EmptyStackException;

    /**
     * Insert an element at the top of the stack.
     *
     * @param element Element to be inserted.
     */
    void push(T element);

    /**
     * Remove the top element from the stack.
     *
     * @return Element removed.
     * @throws EmptyStackException If the stack is empty.
     */
    T pop() throws EmptyStackException;

}
