package com.study.algorithm.sort;


import static com.study.algorithm.util.ArrayUtils.swap;

public class ShakerSort {

    private ShakerSort() {
    }

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int rightSwap = left; // the last index swap moving from left to right
            int leftSwap = right; //the last index swap moving from right to left

            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    rightSwap = i;
                }
            }
            right = rightSwap + 1;

            for (int k = right; k > left; k--) {
                if (array[k] < array[k - 1]) {
                    swap(array, k, k - 1);
                    leftSwap = k - 1;
                }
            }
            left = leftSwap + 1;
        }
    }

}