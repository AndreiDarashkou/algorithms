package com.study.algorithm.dijkstra;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public final class DijkstraAlgorithm {

    public static List<Node> findLowestCostWay(final Node startNode, Node targetNode) {
        Deque<Node> notProcessed = new ArrayDeque<>();
        notProcessed.add(startNode);

        while (!notProcessed.isEmpty()) {
            Node current = notProcessed.pop();
            current.isVisited = true;
            List<Map.Entry<Node, Integer>> neighborList = current.neighborMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toList());

            for (Map.Entry<Node, Integer> entry : neighborList) {
                Node node = entry.getKey();
                if (node.isVisited) {
                    continue;
                }
                if (node.lowestCost == 0 || node.lowestCost > current.lowestCost + entry.getValue()) {
                    node.lowestCost = current.lowestCost + entry.getValue();
                    node.parent = current;
                }
                if (!notProcessed.contains(node)) {
                    notProcessed.addLast(node);
                }
            }
        }
        List<Node> closestWay = new ArrayList<>();
        Node pointer = targetNode;
        do {
            closestWay.add(pointer);
            pointer = pointer.parent;
        } while (pointer != null);
        Collections.reverse(closestWay);

        return closestWay;
    }

}
