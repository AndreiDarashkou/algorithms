package com.study.algorithm.sort;


public class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(int array[]) {
        for (int i = 1; i < array.length; i++) {
            int curVal = array[i];
            int comparedPointer = i - 1;

            while (comparedPointer >= 0 && array[comparedPointer] > curVal) {
                array[comparedPointer + 1] = array[comparedPointer];
                comparedPointer--;
            }
            array[comparedPointer + 1] = curVal;
        }
    }

}
