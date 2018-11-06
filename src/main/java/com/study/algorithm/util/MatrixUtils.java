package com.study.algorithm.util;

import java.math.BigInteger;

import static java.lang.Integer.MAX_VALUE;

public final class MatrixUtils {
    private MatrixUtils() {
    }


    public static int[][] mul(int[][] a, int[][] b) {
        if (a == null || b == null || a[0].length != b.length) {
            throw new IllegalArgumentException("Unsuitable matrices");
        }
        int cols = a.length;
        int rows = b[0].length;
        int res[][] = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
        if (a == null || b == null || a[0].length != b.length) {
            throw new IllegalArgumentException("Unsuitable matrices");
        }
        int cols = a.length;
        int rows = b[0].length;
        BigInteger res[][] = new BigInteger[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                res[i][j] = BigInteger.ZERO;
                for (int k = 0; k < cols; k++) {
                    res[i][j] = res[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return res;
    }


    public static int[][] transfromGridToAdjacencyMatrix(Integer[][] grid) {
        int length = grid.length;
        int size = length * length;
        int[][] adj = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adj[i][j] = i == j ? 0 : MAX_VALUE;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int node = i * length + j;
                if (grid[i][j] != null) {
                    if (i > 0 && grid[i - 1][j] == 0) {
                        adj[node][(i - 1) * length + j] = 1;
                    }
                    if (i < length - 1 && grid[i + 1][j] == 0) {
                        adj[node][(i + 1) * length + j] = 1;
                    }
                    if (j > 0 && grid[i][j - 1] == 0) {
                        adj[node][node - 1] = 1;
                    }
                    if (j < length - 1 && grid[i][j + 1] == 0) {
                        adj[node][node + 1] = 1;
                    }
                }
            }
        }
        return adj;
    }

    public static int[][] generateRandomAdjacencyMatrix(int vertexCount, int maxWeight) {
        int[][] matrix = new int[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix[i][j] = i == j ? 0 : randWeight(maxWeight);
            }
        }
        return matrix;
    }

    private static int randWeight(int maxValue) {
        int nextRand = (int) (Math.random() * (maxValue + maxValue));
        return nextRand >= maxValue ? MAX_VALUE : nextRand + 1;
    }

}
