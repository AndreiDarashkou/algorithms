package com.study.algorithm;


public class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(int mass[]) {
        for (int i = 1; i < mass.length; i++) {
            int curVal = mass[i];
            int comparedPointer = i - 1;

            while (comparedPointer >= 0 && mass[comparedPointer] > curVal) {
                mass[comparedPointer + 1] = mass[comparedPointer];
                comparedPointer--;
            }
            mass[comparedPointer + 1] = curVal;
        }
    }

}
