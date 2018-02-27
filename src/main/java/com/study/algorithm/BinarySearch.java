package com.study.algorithm;

public final class BinarySearch {

    public static Integer findElementPositionRecursive(int[] array, int left, int right, int element) {
        int middle = (right + left) / 2;

        if (element == array[middle]) {
            return middle;
        }
        if (right - left == 1) {
            return null;
        }
        if (element > array[middle]) {
            left = middle;
        } else {
            right = middle;
        }
        return findElementPositionRecursive(array, left, right, element);
    }

    public static Integer findElementPosition(int[] array, int left, int right, int element) {
        int middle = (right + left) / 2;

        while ((right - left) > 1) {
            if (element == array[middle]) {
                return middle;
            }
            if (element > array[middle]) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (right + left) / 2;
        }

        return null;
    }

}
