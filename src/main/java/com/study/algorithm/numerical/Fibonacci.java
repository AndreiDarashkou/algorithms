package com.study.algorithm.numerical;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import static com.study.algorithm.util.MatrixUtils.mul;
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
     * Slow variant. Added caching results for increasing performance.
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
        if (Math.abs(n) < 2) {
            return n < 0 ? BigInteger.valueOf(n).negate() : BigInteger.valueOf(n);
        }

        BigInteger prev = ZERO;
        BigInteger cur = ONE;
        BigInteger fib;
        for (int i = 1; i < Math.abs(n); i++) {
            fib = prev.add(cur);
            prev = cur;
            cur = fib;
        }
        return (n < 0 && n % 2 == 0) ? cur.negate() : cur;
    }

    /**
     * Fast variant. Used multiplying and caching results.
     */
    public static BigInteger calcUsingMatrix(int n) {
        boolean isNegative = n < 0;
        n = Math.abs(n);
        if (n < 2) {
            return BigInteger.valueOf(n);
        }
        Map<Integer, BigInteger[][]> calculated = new TreeMap<>();
        calculated.put(0, new BigInteger[][]{{ONE, ONE}, {ONE, ZERO}});
        calculated.put(1, new BigInteger[][]{{BigInteger.valueOf(2), ONE}, {ONE, ONE}});
        BigInteger result = calculate(INIT, n, calculated)[0][1];
        return (isNegative && n % 2 == 0) ? result.negate() : result;
    }

    private static BigInteger[][] calculate(BigInteger[][] matrix, int n, Map<Integer, BigInteger[][]> calculated) {
        BigInteger[][] calc = calculated.get(n);
        if (calc != null) {
            return calc;
        }
        if (n == 2) {
            return mul(matrix, INIT);
        } else if (n == 3) {
            return mul(mul(matrix, INIT), INIT);
        }
        boolean isEven = n % 2 == 0;
        int m = n / 2;
        calc = mul(calculate(matrix, m, calculated), calculate(matrix, m + (isEven ? 0 : 1), calculated));
        calculated.put(n, calc);
        return calc;
    }

}
