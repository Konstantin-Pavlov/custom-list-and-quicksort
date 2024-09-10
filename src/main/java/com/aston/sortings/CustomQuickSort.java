package com.aston.sortings;

import com.aston.CustomArrayList.CustomArrayList;

/**
 * A utility class that provides a generic implementation of the QuickSort algorithm.
 */
public class CustomQuickSort {

    /**
     * Private constructor to prevent instantiation.
     */
    private CustomQuickSort() {
    }

    /**
     * Sorts the specified CustomArrayList using the QuickSort algorithm.
     *
     * @param list the CustomArrayList to be sorted
     * @param <T>  the type of elements in the list, which must extend Comparable<T>
     * @throws IllegalArgumentException if the elements are not comparable
     */
    public static <T extends Comparable<T>> void sort(CustomArrayList<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Recursively sorts the subarrays before and after the partition index.
     *
     * @param list the CustomArrayList to be sorted
     * @param low  the starting index of the subarray to be sorted
     * @param high the ending index of the subarray to be sorted
     * @param <T>  the type of elements in the list, which must extend Comparable<T>
     */
    private static <T extends Comparable<T>> void quickSort(CustomArrayList<T> list, int low, int high) {
        if (low < high) {
            int pivot = partition(list, low, high);
            quickSort(list, low, pivot - 1);
            quickSort(list, pivot + 1, high);
        }
    }

    /**
     * Partitions the list around the pivot element.
     *
     * @param list the CustomArrayList to be partitioned
     * @param low  the starting index of the subarray to be partitioned
     * @param high the ending index of the subarray to be partitioned
     * @param <T>  the type of elements in the list, which must extend Comparable<T>
     * @return the index of the pivot element after partitioning
     */
    private static <T extends Comparable<T>> int partition(CustomArrayList<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps the elements at the specified positions in the list.
     *
     * @param list the CustomArrayList in which to swap elements
     * @param i    the index of one element to be swapped
     * @param j    the index of the other element to be swapped
     * @param <T>  the type of elements in the list, which must extend Comparable<T>
     */
    private static <T extends Comparable<T>> void swap(CustomArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}