package com.study.algorithm.sort;

public class QuickSort {

    private QuickSort() {
    }

    public static void sort(int array[]) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int array[], int left, int right) {
        if (left >= right) {
            return;
        }
        int delimiter = delimiter(array, left, right);

        sort(array, left, delimiter - 1);
        sort(array, delimiter + 1, right);
    }

    private static int delimiter(int array[], int left, int right) {
        int delimiter = array[left];

        while (true) {
            while (right > left && array[right] >= delimiter) {
                right--;
            }
            array[left] = array[right];

            while (left < right && array[left] < delimiter) {
                left++;
            }
            if (left >= right) {
                array[right] = delimiter;
                break;
            }
            array[right] = array[left];
        }

        return left;
    }

}
