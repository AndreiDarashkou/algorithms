package com.study.algorithm.numerical;

import java.util.*;
import java.util.stream.Collectors;

//https://en.wikipedia.org/wiki/Partition_(number_theory)
public class PartitionNumberSequences {

    public static List<int[]> partition(int val) {
        return partition(val, new HashMap<>(Map.of(1, List.of(new int[]{1}))));
    }

    public static List<int[]> partition(int val, Map<Integer, List<int[]>> cache) {
        if (cache.containsKey(val)) {
            return cache.get(val);
        }

        List<int[]> result = new ArrayList<>();
        for (int level = 1; level < val / 2 + 1; level++) {
            List<int[]> levelResult = shift(level, partition(val - level, cache));
            levelResult.add(new int[]{val});
            result.addAll(levelResult);
        }
        result = new ArrayList<>(result.stream().peek(Arrays::sort)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Arrays::compare))));
        cache.put(val, result);

        return result;
    }

    private static List<int[]> shift(int leftVal, List<int[]> list) {
        List<int[]> merged = new ArrayList<>();
        list.forEach(part -> {
            int[] shifted = new int[part.length + 1];
            shifted[0] = leftVal;
            System.arraycopy(part, 0, shifted, 1, part.length);
            merged.add(shifted);
        });
        return merged;
    }
}