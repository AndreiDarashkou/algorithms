package com.study.algorithm.util;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public final class MatrixUtils {
    private MatrixUtils() {
    }


    public static int[][] mul(int[][] a, int[][] b) {
        if (a == null || b == null || a[0].length != b.length) {
            throw new IllegalArgumentException("Unsuitable matrices");
        }
        int rows = a.length;
        int cols = b[0].length;
        int res[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < a[0].length; k++) {
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

    public static void print(int[][] puzzle) {
        print(puzzle, "|", "---");
    }

    public static void print(int[][] puzzle, String cellDelimiter, String rowDelimiter) {
        StringBuilder builder = new StringBuilder();
        String delimiter = String.join("", Collections.nCopies(puzzle.length, rowDelimiter));
        builder.append(delimiter);
        for (int[] row : puzzle) {
            if (!rowDelimiter.isEmpty()) {
                builder.append("\n");
            }
            builder.append(cellDelimiter);
            for (int col = 0; col < puzzle[0].length; col++) {
                builder.append(row[col]);
                if (row[col] < 10) {
                    builder.append(' ');
                }
                builder.append(cellDelimiter);
            }
            builder.append("\n").append(delimiter);
        }
        System.out.println("\n\n" + builder);
    }

    public static int[][] generateRandom(int rows, int cols, int min, int max) {
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = new Random().nextInt(min + max) - min + 1;
            }
        }

        return result;
    }
}
