package com.study.algorithm.sort;


import com.study.algorithm.util.ArrayUtils;

public final class CountingSort {

    private CountingSort() {
    }

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int min = ArrayUtils.minValue(array);
        int max = ArrayUtils.maxValue(array);
        int[] countArray = new int[max - min + 1]; //for negative values

        for (int i : array) {
            countArray[i - min] = countArray[i - min] + 1;
        }

        int k = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[k] = i + min;
                k++;
            }
        }
    }

}
