package com.study.algorithm.sort;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShellSort {

    private ShellSort() {
    }

    public static void sort(int array[]) {
        List<Integer> incList = incSedgewick(array.length);
        Collections.reverse(incList);

        for (int step : incList) {
            InsertionSort.sort(array, step);
        }
    }

    static List<Integer> incSedgewick(int arraySize) {
        List<Integer> inc = new ArrayList<>();
        int step = -1;

        do {
            if (++step % 2 == 0) {
                inc.add(step, (int) (8 * (1 << step) - 6 * Math.pow(2, (step + 1.0) / 2.0)) + 1);
            } else {
                inc.add(step, (int) (9 * (1 << step) - 9 * Math.pow(2, step / 2.0)) + 1);
            }
        } while (inc.get(step) * 3 < arraySize);

        return inc;
    }

}
