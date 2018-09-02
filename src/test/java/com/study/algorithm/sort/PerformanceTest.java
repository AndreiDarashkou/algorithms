package com.study.algorithm.sort;


import com.study.algorithm.util.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerformanceTest {

    private int[] array;

    @Before
    public void initArray() {
        array = IntStream.rangeClosed(0, 100_000).boxed().collect(
                Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                })).stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void bubbleTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Bubble sort").start();
        BubbleSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void bucketTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Bucket sort").start();
        BucketSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void shakerTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Shaker sort").start();
        ShakerSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void combTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Comb sort").start();
        CombSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void insertionTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Insertion sort").start();
        InsertionSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void shellTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Shell sort").start();
        InsertionSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void selectionTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Selection sort").start();
        InsertionSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void arraysSortTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("java.util.Arrays sort").start();
        Arrays.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void quickSortTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Quick sort").start();
        QuickSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void countingSortTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Counting sort").start();
        CountingSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void heapSortTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Heap sort").start();
        HeapSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }

    @Test
    public void mergeSortTest() {
        int[] copy = array.clone();
        Stopwatch stopwatch = new Stopwatch("Merge sort").start();
        MergeSort.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }
}
