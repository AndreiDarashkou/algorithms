package com.study.puzzle.other;

import java.util.*;
import java.util.stream.Collectors;

public class PartitionNumberGreedy {

    public static List<int[]> partition(int val) {
        Map<Integer, List<int[]>> resMap = new HashMap<>();
        resMap.put(1, List.of(new int[]{1}));

        for (int i = 2; i <= val; i++) {
            List<int[]> prev = resMap.get(i - 1);
            Set<int[]> current = new TreeSet<>(Arrays::compare);

            for (int[] existed : prev) {
                for (int j = 0; j < existed.length; j++) {
                    int[] modified = Arrays.copyOf(existed, existed.length);
                    modified[j] += 1;
                    current.add(modified);
                }
                int[] modified = new int[existed.length + 1];
                System.arraycopy(existed, 0, modified, 0, existed.length);
                modified[existed.length] = 1;
                current.add(modified);
            }

            List<int[]> result = new ArrayList<>(current.stream().peek(Arrays::sort)
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Arrays::compare))));
            resMap.put(i, result);
        }

        return resMap.get(val);
    }
}