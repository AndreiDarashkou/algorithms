package com.study.algorithm.sort;


public class CountingSort {

    private CountingSort() {
    }

    public static void sort(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }

        int min = minValue(array);
        int max = maxValue(array);
        int[] countArray = new int[max - min + 1];

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

    private static int minValue(int[] array) {
        int min = array[0];
        for (int val : array) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    private static int maxValue(int[] array) {
        int max = array[0];
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}
