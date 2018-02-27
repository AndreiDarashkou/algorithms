package com.study.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyAlgorithm {

    public static void chapter7() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> result = new ArrayList<>();

        Map<Integer, List<Integer>> initialMap = new HashMap<>();
        initialMap.put(1, Arrays.asList(1, 2, 3));
        initialMap.put(2, Arrays.asList(4, 5, 6));
        initialMap.put(3, Arrays.asList(8, 9));
        initialMap.put(4, Arrays.asList(1, 2));
        initialMap.put(5, Arrays.asList(1, 8, 9));
        initialMap.put(6, Arrays.asList(1, 7));
        initialMap.put(7, Arrays.asList(1, 8, 9, 4));
        initialMap.put(8, Arrays.asList(4, 7, 2));
        initialMap.put(9, Arrays.asList(1, 2, 7, 3, 4));

        LinkedHashMap<Integer, List<Integer>> sortedMap = initialMap.entrySet().stream().
                sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        for (Map.Entry<Integer, List<Integer>> entry : sortedMap.entrySet()) {
            List<Integer> states = entry.getValue();
            if (isContains(list, states)) {
                list.removeAll(states);
                result.add(entry.getKey());
            }
        }

        System.out.println(result);
    }

    private static boolean isContains(List<Integer> list1, List<Integer> list2) {
        for (int i : list1) {
            for (int j : list2) {
                if (i == j) {
                    return true;
                }
            }
        }
        return false;
    }

}
