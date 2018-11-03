package com.study.algorithm.network.finding_path;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class DijkstraAlgorithm {

    private DijkstraAlgorithm() {
    }

    public static List<Node<Point>> findLowestCostWay(int[][] grid, Point from, Point to) {
        Node<Point> toNode = calculateDistance(grid, from, to);

        List<Node<Point>> closestWay = new ArrayList<>();
        Node<Point> pointer = toNode;
        while (pointer != null && pointer.parent != null) {
            closestWay.add(pointer);
            pointer = pointer.parent;
        }
        Collections.reverse(closestWay);
        return closestWay;
    }

    public static List<Point> findLowestCostPath(int[][] grid, Point from, Point to) {
        Node<Point> toNode = calculateDistance(grid, from, to);

        List<Point> closestWay = new ArrayList<>();
        Node<Point> pointer = toNode;
        while (pointer != null && pointer.parent != null) {
            closestWay.add(pointer.value);
            pointer = pointer.parent;
        }
        Collections.reverse(closestWay);
        return closestWay;
    }

    @SuppressWarnings("unchecked")
    private static Node<Point> calculateDistance(int[][] grid, Point from, Point to) {
        Node<Point>[][] nodeGrid = new Node[grid.length][grid.length];
        Node<Point> toNode = initNodeGrid(nodeGrid, grid, to);

        Deque<Node<Point>> open = new ArrayDeque<>();
        open.add(nodeGrid[from.x][from.y]);

        while (!open.isEmpty()) {
            Node<Point> current = open.pop();
            current.isVisited = true;
            Set<Map.Entry<Node<Point>, Integer>> connections = current.connections.entrySet();
            for (Map.Entry<Node<Point>, Integer> entry : connections) {
                Node<Point> node = entry.getKey();
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
        return toNode;
    }

    private static Node<Point> initNodeGrid(Node<Point>[][] nodeGrid, int[][] grid, Point to) {
        Node<Point> toNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    nodeGrid[i][j] = new Node<>(new Point(i, j));
                    if (to.x == i && to.y == j) {
                        toNode = nodeGrid[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (nodeGrid[i][j] != null) {
                    initConnections(nodeGrid, nodeGrid[i][j]);
                }
            }
        }
        return toNode;
    }

    private static void initConnections(Node<Point>[][] grid, Node<Point> node) {
        node.connections = new HashMap<>();
        int x = node.value.x;
        int y = node.value.y;

        if (x - 1 >= 0 && grid[x - 1][y] != null) {//top
            node.connections.put(grid[x - 1][y], Integer.MAX_VALUE);
        }
        if (y - 1 >= 0 && grid[x][y - 1] != null) {//left
            node.connections.put(grid[x][y - 1], Integer.MAX_VALUE);
        }
        if (y + 1 < grid[x].length && grid[x][y + 1] != null) {//right
            node.connections.put(grid[x][y + 1], Integer.MAX_VALUE);
        }
        if (x + 1 < grid.length && grid[x + 1][y] != null) {//bottom
            node.connections.put(grid[x + 1][y], Integer.MAX_VALUE);
        }
    }

    static class Node<T> {
        private final T value;
        int lowestCost;
        boolean isVisited = false;
        Node<T> parent;
        Map<Node<T>, Integer> connections = new HashMap<>();

        Node(T value) {
            this.value = value;
        }
    }

}
