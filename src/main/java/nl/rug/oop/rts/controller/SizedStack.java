package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;

import java.util.Stack;

/**
 * Stack class that has a maximum size.
 *
 * @param <T> The type of the stack.
 */
@AllArgsConstructor
public class SizedStack<T> extends Stack<T> {
    /**
     * The maximum size of the stack.
     */
    private int maxSize;

    @Override
    public T push(T item) {
        if (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(item);
    }
}
