package oopHomeWork8.stack;

import java.util.NoSuchElementException;

public interface Stack {

    /**
     * Retrieves and removes the head of the stack,
     * or returns {@code null} if this stack is empty.
     * @return the first element of this stack, or {@code null} if
     *         this stack is empty
     */
    Object pop();

    /**
     * Retrieves, but does not remove, the head of the stack.
     * This method differs from {@link #peek peek} only in that it throws an
     * exception if this stack is empty.
     * @return the head of the stack
     * @throws NoSuchElementException if this stack is empty
     */
    Object element();

    /**
     * Retrieves, but does not remove, the head of the stack,
     * or returns {@code null} if this stack is empty.
     * @return the head of the stack, or {@code null} if this stack is empty
     */
    Object peek();

    /**
     * Pushes an element onto the stack.
     * @param o the element to push
     */
    void push(Object o);

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    int size();

    /**
     * Removes all of the elements from this stack. (optional operation).
     * The stack will be empty after this method returns.
     */
    void clear();

}
