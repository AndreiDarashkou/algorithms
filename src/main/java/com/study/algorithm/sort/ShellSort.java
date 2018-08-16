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
        int[] incArray = incList.stream().mapToInt(Integer::intValue).toArray();

        for (int step : incArray) {
            for (int j = step; j < array.length; j += step) {
                int curVal = array[j];
                int comparedPointer = j - step;

                while (comparedPointer >= 0 && array[comparedPointer] > curVal) {
                    array[comparedPointer + step] = array[comparedPointer];
                    comparedPointer -= step;
                }
                array[comparedPointer + step] = curVal;
            }
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
