package com.study.algorithm;


import com.study.algorithm.sort.BubbleSort;
import com.study.algorithm.sort.CombSort;
import com.study.algorithm.sort.InsertionSort;
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
        array = IntStream.rangeClosed(0, 50_000).boxed().collect(
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
        Stopwatch stopwatch = new Stopwatch("Selection sort").start();
        Arrays.sort(copy);
        System.out.println(stopwatch.stop().prettyPrintString(TimeUnit.SECONDS));
    }
}
