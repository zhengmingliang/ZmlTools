package top.wys.utils.collection;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack extends ArrayList implements Buffer {

    /** Ensure serialization compatibility */
    private static final long serialVersionUID = 2130079159931574599L;

    /**
     * Constructs a new empty <code>ArrayStack</code>. The initial size
     * is controlled by <code>ArrayList</code> and is currently 10.
     */
    public ArrayStack() {
        super();
    }

    /**
     * Constructs a new empty <code>ArrayStack</code> with an initial size.
     *
     * @param initialSize  the initial size to use
     * @throws IllegalArgumentException  if the specified initial size
     *  is negative
     */
    public ArrayStack(int initialSize) {
        super(initialSize);
    }

    /**
     * Return <code>true</code> if this stack is currently empty.
     * <p>
     * This method exists for compatibility with <code>java.util.Stack</code>.
     * New users of this class should use <code>isEmpty</code> instead.
     *
     * @return true if the stack is currently empty
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * Returns the top item off of this stack without removing it.
     *
     * @return the top item on the stack
     * @throws EmptyStackException  if the stack is empty
     */
    public Object peek() throws EmptyStackException {
        int n = size();
        if (n <= 0) {
            throw new EmptyStackException();
        } else {
            return get(n - 1);
        }
    }

    /**
     * Returns the n'th item down (zero-relative) from the top of this
     * stack without removing it.
     *
     * @param n  the number of items down to go
     * @return the n'th item on the stack, zero relative
     * @throws EmptyStackException  if there are not enough items on the
     *  stack to satisfy this request
     */
    public Object peek(int n) throws EmptyStackException {
        int m = (size() - n) - 1;
        if (m < 0) {
            throw new EmptyStackException();
        } else {
            return get(m);
        }
    }

    /**
     * Pops the top item off of this stack and return it.
     *
     * @return the top item on the stack
     * @throws EmptyStackException  if the stack is empty
     */
    public Object pop() throws EmptyStackException {
        int n = size();
        if (n <= 0) {
            throw new EmptyStackException();
        } else {
            return remove(n - 1);
        }
    }

    /**
     * Pushes a new item onto the top of this stack. The pushed item is also
     * returned. This is equivalent to calling <code>add</code>.
     *
     * @param item  the item to be added
     * @return the item just pushed
     */
    public Object push(Object item) {
        add(item);
        return item;
    }

    /**
     * Returns the one-based position of the distance from the top that the
     * specified object exists on this stack, where the top-most element is
     * considered to be at distance <code>1</code>.  If the object is not
     * present on the stack, return <code>-1</code> instead.  The
     * <code>equals()</code> method is used to compare to the items
     * in this stack.
     *
     * @param object  the object to be searched for
     * @return the 1-based depth into the stack of the object, or -1 if not found
     */
    public int search(Object object) {
        int i = size() - 1;        // Current index
        int n = 1;                 // Current distance
        while (i >= 0) {
            Object current = get(i);
            if ((object == null && current == null) ||
                    (object != null && object.equals(current))) {
                return n;
            }
            i--;
            n++;
        }
        return -1;
    }

    /**
     * Returns the element on the top of the stack.
     *
     * @return the element on the top of the stack
     * @throws java.nio.BufferUnderflowException  if the stack is empty
     */
    public Object get() {
        int size = size();
        if (size == 0) {
            throw new BufferUnderflowException();
        }
        return get(size - 1);
    }

    /**
     * Removes the element on the top of the stack.
     *
     * @return the removed element
     * @throws BufferUnderflowException  if the stack is empty
     */
    public Object remove() {
        int size = size();
        if (size == 0) {
            throw new BufferUnderflowException();
        }
        return remove(size - 1);
    }

}
