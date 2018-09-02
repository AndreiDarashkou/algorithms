package com.study.algorithm.util;


import java.util.List;

public class ArrayUtils {

    private ArrayUtils(){}

    public static void swap(int mass[], int i, int j) {
        int tmp = mass[i];
        mass[i] = mass[j];
        mass[j] = tmp;
    }

    public static int minValue(int[] array) {
        int min = array[0];
        for (int val : array) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    public static int maxValue(int[] array) {
        int max = array[0];
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public static int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}
