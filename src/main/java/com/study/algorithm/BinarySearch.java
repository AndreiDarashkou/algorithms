package com.study.algorithm;

public final class BinarySearch {

    private BinarySearch() {
    }

    public static Integer findElementPositionRecursive(int[] array, int element) {
        if (array == null || array.length == 0) {
            return -1;
        }
        return findElementPositionRecursive(array, 0, array.length - 1, element);
    }

    private static int findElementPositionRecursive(int[] array, int left, int right, int element) {
        int middle = (left + right) / 2;

        if (right - left == 1 || left == right) {
            if (element == array[left]) {
                return left;
            }
            if (element == array[right]) {
                return right;
            }
            return -1;
        } else if (element > array[middle]) {
            left = middle;
        } else {
            right = middle;
        }
        return findElementPositionRecursive(array, left, right, element);
    }

    public static int findElementPosition(int[] array, int element) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        int middle = (right + left);

        while (right - left > 1) {
            if (element > array[middle]) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (right + left) / 2;
        }

        if (right - left == 1 || left == right) {
            if (element == array[left]) {
                return left;
            }
            if (element == array[right]) {
                return right;
            }
        }

        return -1;
    }

}
