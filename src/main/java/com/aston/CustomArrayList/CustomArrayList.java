package com.aston.CustomArrayList;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * A custom implementation of an ArrayList.
 *
 * <p>This implementation supports dynamic resizing and ensures that only elements
 * that implement the {@link Comparable} interface can be added to the list. The list
 * can be sorted using the natural ordering of its elements.</p>
 *
 * @param <T> the type of elements in this list, which must extend {@link Comparable}
 */
public class CustomArrayList<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private final Logger logger;

    /**
     * Constructs an empty list with an initial capacity of 8.
     */
    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        this.data = (T[]) new Object[8];
        this.size = 0;
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @return an iterator over the elements in this list
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new CustomListIterator();
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    public void add(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException (from indexCheck method call) if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index) {
        indexCheck(index);
        return data[index];
    }

    /**
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   the index of the element to replace
     * @param element the element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public void set(int index, T element) {
        indexCheck(index);
        data[index] = element;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param element the element to check for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(T element) {
        if (size == 0) {
            logger.warning("the list is empty");
            return false;
        }
        if (element == null) {
            return Arrays.stream(data, 0, size)
                    .anyMatch(Objects::isNull);
        }
        return Arrays.stream(data, 0, size)
                .filter(Objects::nonNull)
                .anyMatch(e -> e.equals(element));
    }

    /**
     * Deletes the element at the specified index.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * The last element is set to null to avoid memory leaks.
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException (from indexCheck method call) if the index is out of range (index < 0 || index >= size)
     */
    public T delete(int index) {
        indexCheck(index);
        T element = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null; // Clear the last element
        return element;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Sorts the elements in this list in natural order.
     *
     * <p>This method uses the {@link Arrays#sort(Object[])} method to sort the elements
     * in the list. The elements must implement the {@link Comparable} interface
     * to be sorted. If the list contains fewer than two elements, the method
     * returns immediately.</p>
     */
    @SuppressWarnings("unchecked")
    public void sort() {
        if (sizeLessThanTwo()) return;
        T[] arrayToSort = (T[]) new Comparable[size];
        System.arraycopy(data, 0, arrayToSort, 0, size);
        Arrays.sort(arrayToSort);
        System.arraycopy(arrayToSort, 0, data, 0, size);
    }

    /**
     * Sorts the elements in this list using the specified comparator.
     *
     * <p>This method uses the {@link Arrays#sort(Object[], Comparator)} method to sort the elements
     * in the list based on the provided comparator. If the list contains fewer than two elements,
     * the method returns immediately.</p>
     *
     * @param comparator the comparator to determine the order of the list
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> comparator) {
        if (sizeLessThanTwo()) return;
        checkIfInstanceofComparable();
        // Create a new array of type T[] using reflection and copy elements from data
        T[] arrayToSort = (T[]) new Comparable[size];
        System.arraycopy(data, 0, arrayToSort, 0, size);
        Arrays.sort(arrayToSort, comparator);
        System.arraycopy(arrayToSort, 0, data, 0, size);
    }

    /**
     * Removes all elements from the collection.
     * Sets the size to 0 and nullifies all elements in the array to allow garbage collection.
     */
    public void clean() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    /**
     * Checks if type implements {@link Comparable} interface.
     */
    private void checkIfInstanceofComparable() {
        if (!(data[0] instanceof Comparable)) {
            throw new IllegalArgumentException("Elements are not comparable");
        }
    }

    /**
     * Checks if size of a list is less than two.
     */
    private boolean sizeLessThanTwo() {
        return size < 2;
    }

    /**
     * Checks if the given index is within the bounds of the list.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    private void indexCheck(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds, " + size);
        }
    }

    /**
     * Resizes the internal array to accommodate more elements.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * Returns a string representation of this list.
     * The string representation consists of a list of the list's elements in the order they are returned by its iterator, enclosed in square brackets ("[]").
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        String fmt = "CustomArrayList [size=%d, data=%s]%n";
        return String.format(fmt, size, Arrays.stream(data)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(","))
        );
    }

    /**
     * CustomListIterator implementation.
     */
    private class CustomListIterator implements Iterator<T> {
        private int currentIndex;

        /**
         * @return true if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            return data[currentIndex++];
        }
    }
}

