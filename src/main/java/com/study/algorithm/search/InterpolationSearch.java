package com.study.algorithm.search;

public final class InterpolationSearch {

    private InterpolationSearch() {
    }

    public static int search(int[] array, int element) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int min = 0;
        int max = array.length - 1;

        while (min < max) {
            int middle = middle(array, min, max, element);
            if (array[middle] == element) {
                return middle;
            }
            if (array[middle] > element) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }

        return -1;
    }

    static int middle(int[] array, int minIndex, int maxIndex, int element) {
        return minIndex + (maxIndex - minIndex) * (element - array[minIndex]) / (array[maxIndex] - array[minIndex]);
    }


}
