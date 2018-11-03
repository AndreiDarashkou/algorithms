package com.study.algorithm.network.finding_path;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class DijkstraAlgorithm {

    private DijkstraAlgorithm() {
    }

    public static List<Node<Point>> findLowestCostWay(Integer[][] grid, Point from, Point to) {
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

    public static List<Point> findLowestCostPath(Integer[][] grid, Point from, Point to) {
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
    private static Node<Point> calculateDistance(Integer[][] grid, Point from, Point to) {
        Node<Point>[][] nodeGrid = new Node[grid.length][grid.length];
        Node<Point> toNode = initNodeGrid(nodeGrid, grid, to);

        Deque<Node<Point>> open = new ArrayDeque<>();
        Node<Point> fromNode = nodeGrid[from.x][from.y];
        fromNode.lowestCost = 0;
        open.add(fromNode);

        while (!open.isEmpty()) {
            Node<Point> current = open.pop();
            current.isVisited = true;
            Set<Map.Entry<Node<Point>, Integer>> connections = current.connections.entrySet();//sort
            for (Map.Entry<Node<Point>, Integer> entry : connections) {
                Node<Point> node = entry.getKey();
                if (node.isVisited) {
                    continue;
                }
                if (node.parent == null || node.lowestCost == 0 || node.lowestCost > current.lowestCost + entry.getValue()) {
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

    private static Node<Point> initNodeGrid(Node<Point>[][] nodeGrid, Integer[][] grid, Point to) {
        Node<Point> toNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != null) {
                    nodeGrid[i][j] = new Node<>(new Point(i, j));
                    nodeGrid[i][j].lowestCost = grid[i][j];
                    if (to.x == i && to.y == j) {
                        toNode = nodeGrid[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (nodeGrid[i][j] != null) {
                    initConnections(nodeGrid, grid, nodeGrid[i][j]);
                }
            }
        }
        return toNode;
    }

    private static void initConnections(Node<Point>[][] nodeGrid, Integer[][] grid, Node<Point> node) {
        node.connections = new HashMap<>();
        int x = node.value.x;
        int y = node.value.y;

        if (x - 1 >= 0 && nodeGrid[x - 1][y] != null) {//top
            node.connections.put(nodeGrid[x - 1][y], grid[x - 1][y]);
        }
        if (y - 1 >= 0 && nodeGrid[x][y - 1] != null) {//left
            node.connections.put(nodeGrid[x][y - 1], grid[x][y - 1]);
        }
        if (y + 1 < nodeGrid[x].length && nodeGrid[x][y + 1] != null) {//right
            node.connections.put(nodeGrid[x][y + 1], grid[x][y + 1]);
        }
        if (x + 1 < nodeGrid.length && nodeGrid[x + 1][y] != null) {//bottom
            node.connections.put(nodeGrid[x + 1][y], grid[x + 1][y]);
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
