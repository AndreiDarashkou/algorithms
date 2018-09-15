package com.study.algorithm.numerical;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public final class Fibonacci {

    private final static BigInteger[][] INIT = new BigInteger[][]{{ONE, ONE}, {ONE, ZERO}};

    private Fibonacci() {
    }

    /**
     * Very slow and simple variant
     */
    public static long calcRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return calcRecursive(n - 1) + calcRecursive(n - 2);
    }

    /**
     * Slow variant. Save calculated fibonacci numbers to avoid recompute
     */
    public static BigInteger calcRecursiveWithoutDuplicates(int n) {
        BigInteger[] calculated = new BigInteger[1000000];
        calculated[0] = ZERO;
        calculated[1] = ONE;
        return calcRecursive(n, calculated);
    }

    private static BigInteger calcRecursive(int n, BigInteger[] calculated) {
        if (calculated[n] == null) {
            calculated[n] = calcRecursive(n - 1, calculated).add(calcRecursive(n - 2, calculated));
        }
        return calculated[n];
    }

    /**
     * Without recursive. Supports negative values.
     */
    public static BigInteger calcWithoutRecursive(int n) {
        boolean isNegative = n < 0;
        if (Math.abs(n) < 2) {
            return isNegative ? BigInteger.valueOf(n).negate() : BigInteger.valueOf(n);
        }
        int increment = isNegative ? -1 : 1;

        BigInteger prev = ZERO;
        BigInteger cur = isNegative ? ONE.negate() : ONE;
        BigInteger fib;
        for (int i = isNegative ? -1 : 1; isNegative ? i > n : i < n; i += increment) {
            fib = isNegative ? prev.subtract(cur) : prev.add(cur);
            prev = cur;
            cur = fib;
        }
        return isNegative ? cur.negate() : cur;
    }

    /**
     * Fast variant. Used multiplying and caching results.
     */
    public static BigInteger calcUsingMatrix(int n) {
        if (Math.abs(n) < 2) {
            return BigInteger.valueOf(n);
        }
        Map<Integer, BigInteger[][]> calculated = new TreeMap<>();
        calculated.put(0, new BigInteger[][]{{ONE, ONE}, {ONE, ZERO}});
        calculated.put(1, new BigInteger[][]{{BigInteger.valueOf(2), ONE}, {ONE, ONE}});
        return calculate(INIT, n, calculated)[0][1];
    }

    private static BigInteger[][] calculate(BigInteger[][] matrix, int n, Map<Integer, BigInteger[][]> calculated) {
        if (calculated.get(n) != null) {
            return calculated.get(n);
        }
        if (n == 2) {
            return multiply(matrix, INIT);
        } else if (n == 3) {
            return multiply(multiply(matrix, INIT), INIT);
        }
        boolean isEven = n % 2 == 0;
        int m = n / 2;
        BigInteger[][] calc = multiply(calculate(matrix, m, calculated), calculate(matrix, m + (isEven ? 0 : 1), calculated));
        calculated.put(n, calc);
        return calc;
    }

    private static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
        BigInteger res[][] = new BigInteger[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = BigInteger.ZERO;
                for (int k = 0; k < 2; k++) {
                    res[i][j] = res[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return res;
    }

}
