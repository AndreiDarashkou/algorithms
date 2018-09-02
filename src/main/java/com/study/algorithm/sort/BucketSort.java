package com.study.algorithm.sort;


import com.study.algorithm.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.study.algorithm.util.ArrayUtils.toArray;

public class BucketSort {

    private BucketSort() {
    }

    public static void sort(int[] array) {
        sort(array, (int) Math.ceil(Math.pow(array.length, 0.5)));
    }

    public static void sort(int[] array, int bucketSize) {
        if (array == null || array.length < 2) {
            return;
        }
        int min = ArrayUtils.minValue(array);
        int max = ArrayUtils.maxValue(array);
        int range = max - min;
        List<Integer>[] buckets = getBuckets(bucketSize, range);
        for (int val : array) {
            buckets[(val - min) / bucketSize].add(val);
        }
        int resIndex = 0;
        for (List<Integer> bucket : buckets) {
            int[] arrBucket = toArray(bucket);
            InsertionSort.sort(arrBucket);
            for (int val : arrBucket) {
                array[resIndex] = val;
                resIndex++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] getBuckets(int bucketSize, int range) {
        List<Integer>[] buckets = new ArrayList[range / bucketSize + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        return buckets;
    }

}
