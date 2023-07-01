package top.wys.utils.collection;

import java.util.Collection;

public interface Buffer extends Collection {

    /**
     * Gets and removes the next object from the buffer.
     *
     * @return the next object in the buffer, which is also removed
     * @throws java.nio.BufferUnderflowException if the buffer is already empty
     */
    Object remove();

    /**
     * Gets the next object from the buffer without removing it.
     *
     * @return the next object in the buffer, which is not removed
     * @throws java.nio.BufferUnderflowException if the buffer is empty
     */
    Object get();

}

