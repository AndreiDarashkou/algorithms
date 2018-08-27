package com.study.algorithm.sort;


public class MergeSort {

    public static void sort(int[] array) {
        sort(array, new int[array.length], 0, array.length - 1);
    }


    private static void sort(int array[], int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int medium = (left + right) / 2;
        sort(array, temp, left, medium);
        sort(array, temp, medium + 1, right);

        int leftIndex = left;
        int rightIndex = medium + 1;
        int tempIndex = leftIndex;

        while (leftIndex <= medium && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                temp[tempIndex] = array[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = array[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        for (int i = leftIndex; i <= medium; i++) {
            temp[tempIndex] = array[i];
            tempIndex++;
        }
        for (int i = rightIndex; i <= right; i++) {
            temp[tempIndex] = array[i];
            tempIndex++;
        }
        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }
}
