package com.study.algorithm.sort;


import static com.study.algorithm.util.ArrayUtils.swap;

public class CombSort {

    private CombSort() {
    }

    public static void sort(int array[]) {

        int gap = array.length;
        boolean isSorted = false;

        while (gap >= 1 || !isSorted) {
            gap = Math.max((int) (gap / 1.3), 1);
            isSorted = true;

            for (int i = 0; i < array.length - gap; i++) {
                if (array[i] > array[i + gap]) {
                    swap(array, i, i + gap);
                    isSorted = false;
                }
            }
            gap /= 1.3;
        }
    }
}
