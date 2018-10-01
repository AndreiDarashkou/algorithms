package com.study.algorithm.finding_path;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class DijkstraAlgorithm {

    public static <T> List<Node<T>> findLowestCostWay(Node<T> startNode, Node<T> targetNode) {
        Deque<Node<T>> open = new ArrayDeque<>();
        open.add(startNode);

        while (!open.isEmpty()) {
            Node<T> current = open.pop();
            current.isVisited = true;
            List<Map.Entry<Node<T>, Integer>> neighborList = current.connections.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toList());

            for (Map.Entry<Node<T>, Integer> entry : neighborList) {
                Node<T> node = entry.getKey();
                if (node.isVisited) {
                    continue;
                }
                if (node.lowestCost == 0 || node.lowestCost > current.lowestCost + entry.getValue()) {
                    node.lowestCost = current.lowestCost + entry.getValue();
                    node.parent = current;
                }
                if (!open.contains(node)) {
                    open.addLast(node);
                }
            }
        }
        List<Node<T>> closestWay = new ArrayList<>();
        Node<T> pointer = targetNode;
        do {
            closestWay.add(pointer);
            pointer = pointer.parent;
        } while (pointer != null);
        Collections.reverse(closestWay);

        return closestWay;
    }

    public static List<Point> findLowestCostWay(int[][] grid, int fromX, int fromY, int toX, int toY) {
        List<Node<Point>> nodeList = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) { //active == 0, inactive == 1
                    continue;
                }
                nodeList.add(new Node<>(new Point(row, col)));
            }
        }
        for (Node<Point> node : nodeList) {
            Map<Node<Point>, Integer> connections = node.connections;
            for (Node<Point> node2 : nodeList) {
                if (isNeighbours(node, node2)) {
                    connections.put(node2, 1);
                }
            }
        }
        Node<Point> from = find(fromX, fromY, nodeList);
        Node<Point> to = find(toX, toY, nodeList);
        List<Node<Point>> way = findLowestCostWay(from, to);
        way.remove(0);
        return way.stream().map(Node::getValue).collect(toList());
    }

    private static Node<Point> find(int x, int y, List<Node<Point>> nodeList) {
        Point p = new Point(x, y);
        for (Node<Point> node : nodeList) {
            if (node.getValue().equals(p)) {
                return node;
            }
        }
        throw new IllegalArgumentException();
    }

    private static boolean isNeighbours(Node<Point> node, Node<Point> node2) {
        Point p1 = node.getValue();
        Point p2 = node2.getValue();
        return ((p1.x == p2.x) && Math.abs(p1.y - p2.y) == 1) || ((p1.y == p2.y) && Math.abs(p1.x - p2.x) == 1);
    }

}
