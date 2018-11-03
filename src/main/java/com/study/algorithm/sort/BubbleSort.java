package com.study.algorithm.sort;

import static com.study.algorithm.util.ArrayUtils.swap;

public class BubbleSort {

    private BubbleSort() {
    }

    public static void sort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

}
