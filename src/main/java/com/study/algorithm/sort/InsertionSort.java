package com.study.algorithm.sort;


public class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(int array[]) {
        sort(array, 1);
    }

    static void sort(int array[], int step) {
        for (int i = step; i < array.length; i += step) {
            int curVal = array[i];
            int comparedPointer = i - step;

            while (comparedPointer >= 0 && array[comparedPointer] > curVal) {
                array[comparedPointer + step] = array[comparedPointer];
                comparedPointer -= step;
            }
            array[comparedPointer + step] = curVal;
        }
    }

}
