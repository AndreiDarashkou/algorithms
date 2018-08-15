package com.study.algorithm.util;


public class ArrayUtils {

    private ArrayUtils(){}

    public static void swap(int mass[], int i, int j) {
        int tmp = mass[i];
        mass[i] = mass[j];
        mass[j] = tmp;
    }

}
