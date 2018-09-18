package com.study.algorithm.numerical;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.study.algorithm.numerical.NumericalAlgorithms.findPrimes;
import static java.math.BigInteger.ONE;

public final class Factorial {

    private final static BigInteger TWO = BigInteger.valueOf(2);

    private Factorial() {
    }

    public static BigInteger calcRecursive(BigInteger num) {
        if (num.longValue() <= 1) {
            return ONE;
        }
        return num.multiply(calcRecursive(num.subtract(ONE)));
    }

    public static BigInteger calculate(long num) {
        if (num <= 1) {
            return ONE;
        }
        BigInteger result = ONE;
        for (long i = num; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger calcUsingTreeMul(int num) {
        if (num <= 2) {
            return BigInteger.valueOf(num);
        }
        return treeMul(TWO, BigInteger.valueOf(num));
    }

    private static BigInteger treeMul(BigInteger st, BigInteger end) {
        if (st.equals(end)) {
            return end;
        }
        if (end.subtract(st).equals(ONE)) {
            return st.multiply(end);
        }
        BigInteger middle = st.add(end).divide(TWO);
        return treeMul(st, middle).multiply(treeMul(middle.add(ONE), end));
    }

    public static BigInteger caclUsingPrimeFactorization(int num) {
        if (num <= 2) {
            return BigInteger.valueOf(num);
        }
        Map<Integer, Integer> primes = findPrimeFactorsPower(num);
        BigInteger result = ONE;
        for (Map.Entry<Integer, Integer> prime : primes.entrySet()) {
            result = result.multiply(BigInteger.valueOf(prime.getKey()).pow(prime.getValue()));
        }
        return result;
    }

    private static Map<Integer, Integer> findPrimeFactorsPower(int number) {
        Map<Integer, Integer> primeFactors = new TreeMap<>();

        List<Integer> primes = findPrimes(number);
        primes.remove(0); //remove 2

        for (int prime : primes) {
            int power = 0;
            int primeCopy = prime;
            while (number / primeCopy >= 1) {
                power += number / primeCopy;
                primeCopy *= prime;
            }
            primeFactors.put(prime, power);
        }
        return primeFactors;
    }

}
