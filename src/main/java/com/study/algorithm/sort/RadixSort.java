package com.study.algorithm.sort;

import com.study.algorithm.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.study.algorithm.util.ArrayUtils.toArray;

public final class RadixSort {

    private RadixSort() {
    }

    public static void sort(int[] array) {
        int max = ArrayUtils.maxValue(array);
        int radix = (int) Math.log10(max);
        int base = (int) Math.pow(10, radix);
        bucketSort(array, base);
    }


    public static void bucketSort(int[] array, int base) {
        if (array == null || array.length < 2) {
            return;
        }
        if (base == 1) {
            CountingSort.sort(array);
            return;
        }
        List<Integer>[] buckets = getBuckets();
        for (int val : array) {
            int index = (val % (base * 10)) / base;
            buckets[index].add(val);
        }
        int resIndex = 0;
        for (List<Integer> bucket : buckets) {
            int[] arrBucket = toArray(bucket);
            bucketSort(arrBucket, base / 10);
            for (int val : arrBucket) {
                array[resIndex] = val;
                resIndex++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] getBuckets() {
        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        return buckets;
    }

}
