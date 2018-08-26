package com.study.algorithm.sort;


import static com.study.algorithm.util.ArrayUtils.swap;

public class HeapSort {

    private HeapSort() {
    }

    public static void sort(int[] array) {
        makeHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = removeTopElement(array, i);
        }
    }

    static int removeTopElement(int[] array, int length) {
        int topElement = array[0];

        array[0] = array[length];
        int index = 0;
        while (true) {
            int child1 = index * 2 + 1;
            int child2 = index * 2 + 2;

            if (child1 > length) {
                child1 = index;
            }
            if (child2 > length) {
                child2 = index;
            }
            if (array[index] >= array[child1] && array[index] >= array[child2]) {
                break;
            }
            int swapIndex = array[child1] > array[child2] ? child1 : child2;
            swap(array, index, swapIndex);
            index = swapIndex;
        }

        return topElement;
    }

    static void makeHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (array[parent] >= array[index]) {
                    break;
                }
                swap(array, index, parent);
                index = parent;
            }
        }
    }

}
