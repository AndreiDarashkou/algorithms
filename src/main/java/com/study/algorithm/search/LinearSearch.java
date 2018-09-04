package com.study.algorithm.search;

public final class LinearSearch {

    private LinearSearch(){
    }

    public static int search(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i]) {
                return i;
            }
        }
        return -1;
    }

}
