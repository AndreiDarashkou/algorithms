package com.study.algorithm.util;

import java.math.BigInteger;

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

}
