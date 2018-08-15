package com.study.algorithm;

import static com.study.algorithm.util.ArrayUtils.swap;

public final class SelectionSort {

    private SelectionSort() {
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = min; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

}
