package com.study.algorithm.network.finding_path;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.study.algorithm.util.MatrixUtils.transfromGridToAdjacencyMatrix;
import static java.lang.Integer.MAX_VALUE;

public final class FloydWarshallAlgorithm {

    private FloydWarshallAlgorithm() {
    }

    public static List<Integer> findPath(Integer[][] grid, Point from, Point to) {
        int[][] adj = transfromGridToAdjacencyMatrix(grid);
        int[][] via = calculateDistance(adj);
        int size = grid.length;
        return findPath(size * from.x + from.y, size * to.x + to.y, adj, via);
    }

    private static int[][] calculateDistance(int[][] adjMatrix) {
        int[][] viaMatrix = createViaMatrix(adjMatrix);
        int length = adjMatrix.length;
        for (int via = 0; via < length; via++) {
            for (int from = 0; from < length; from++) {
                for (int to = 0; to < length; to++) {
                    if (adjMatrix[from][via] == MAX_VALUE || adjMatrix[via][to] == MAX_VALUE) {
                        continue;
                    }
                    int distance = adjMatrix[from][via] + adjMatrix[via][to];
                    if (distance < adjMatrix[from][to]) {
                        adjMatrix[from][to] = distance;
                        viaMatrix[from][to] = via;
                    }
                }
            }
        }
        return viaMatrix;
    }

    private static int[][] createViaMatrix(int[][] adjMatrix) {
        int length = adjMatrix.length;
        int[][] viaMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (adjMatrix[i][j] < MAX_VALUE) {
                    viaMatrix[i][j] = j;
                } else {
                    viaMatrix[i][j] = -1;
                }
            }
        }
        return viaMatrix;
    }

    private static List<Integer> findPath(int startNode, int endNode, int[][] distance, int[][] via) {
        if (distance[startNode][endNode] == MAX_VALUE) {
            return Collections.emptyList();
        }
        int viaNode = via[startNode][endNode];
        if (viaNode == endNode) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(endNode);
            return result;
        } else {
            List<Integer> path = findPath(startNode, viaNode, distance, via);
            path.addAll(findPath(viaNode, endNode, distance, via));
            return path;
        }
    }

}
