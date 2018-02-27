package com.study.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public final class DijkstraAlgorithm {

    public static void chapter6() {
        Node node1 = new Node(1);
        node1.lowestCost = 0;
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.neighborMap.put(node2, 7);
        node1.neighborMap.put(node3, 9);
        node1.neighborMap.put(node6, 14);

        node2.neighborMap.put(node1, 7);
        node2.neighborMap.put(node3, 10);
        node2.neighborMap.put(node4, 15);

        node3.neighborMap.put(node1, 9);
        node3.neighborMap.put(node2, 10);
        node3.neighborMap.put(node4, 11);
        node3.neighborMap.put(node6, 2);

        node4.neighborMap.put(node2, 15);
        node4.neighborMap.put(node3, 11);
        node4.neighborMap.put(node5, 6);

        node5.neighborMap.put(node4, 6);
        node5.neighborMap.put(node6, 9);

        node6.neighborMap.put(node1, 14);
        node6.neighborMap.put(node3, 2);
        node6.neighborMap.put(node5, 9);

        Deque<Node> notProcessed = new ArrayDeque<>();
        notProcessed.add(node1);

        while (!notProcessed.isEmpty()) {
            Node current = notProcessed.pop();
            current.isVisited = true;
            List<Map.Entry<Node, Integer>> neighborList = current.neighborMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toList());

            for (Map.Entry<Node, Integer> entry : neighborList) {
                Node node = entry.getKey();
                if (node.isVisited) continue;
                if (node.lowestCost == null || node.lowestCost > current.lowestCost + entry.getValue()) {
                    node.lowestCost = current.lowestCost + entry.getValue();
                    node.parent = current;
                }
                if (!notProcessed.contains(node)) {
                    notProcessed.addLast(node);
                }
            }
        }

        System.out.print("Finish");
    }

    public static class Node {
        int id;
        Integer lowestCost;
        boolean isVisited = false;
        Node parent;
        Map<Node, Integer> neighborMap = new HashMap<>();

        Node(int id) {
            this.id = id;
        }

    }

}
