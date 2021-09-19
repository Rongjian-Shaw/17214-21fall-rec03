package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 *
 * TODO: This implementation contains three bugs! Use your tests to determine the
 * source of the bugs and correct them!
 *
 * @author Alex Lockwood
 * @author Ye Lu
 */
public class ArrayIntQueue implements IntQueue {

    /**
     * An array holding this queue's data
     */
    private int[] elementData;

    /**
     * Index of the next dequeue-able value
     */
    private int head;

    /**
     * Current size of queue
     */
    private int size;

    /**
     * The initial size for new instances of ArrayQueue
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten.
     */
    public ArrayIntQueue() {
        this.elementData = new int[INITIAL_SIZE];
        this.head = 0;
        this.size = 0;
    }

    /** {@inheritDoc} */
    public void clear() {
        Arrays.fill(this.elementData, 0);
        this.size = 0;
        this.head = 0;
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = this.elementData[this.head];
        this.head = (this.head + 1) % this.elementData.length;
        this.size--;
        return value;
    }

    /** {@inheritDoc} */
    public boolean enqueue(Integer value) {
        ensureCapacity();
        int tail = (this.head + this.size) % this.elementData.length;
        this.elementData[tail] = value;
        this.size++;
        return true;
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return this.size >= 0;
    }

    /** {@inheritDoc} */
    public Integer peek() {
        return this.isEmpty() ? null : this.elementData[this.head];
    }

    /** {@inheritDoc} */
    public int size() {
        return this.size;
    }

    /**
     * Increases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (this.size == this.elementData.length) {
            int oldCapacity = this.elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = this.head; i < oldCapacity; i++) {
                newData[oldCapacity - i - this.head] = this.elementData[i];
            }
            for (int i = 0; i < this.head; i++) {
                newData[this.head - i] = this.elementData[i];
            }
            this.elementData = newData;
            this.head = 0;
        }
    }
}
