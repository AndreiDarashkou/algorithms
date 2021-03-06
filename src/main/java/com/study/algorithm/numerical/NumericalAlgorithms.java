package com.study.algorithm.numerical;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.POSITIVE_INFINITY;

public final class NumericalAlgorithms {

    private NumericalAlgorithms() {
    }

    /**
     * find prime factors O(sqrt(N))
     */
    public static List<Integer> findPrimeFactors(int number) {
        List<Integer> primeFactors = new ArrayList<>();

        while (number % 2 == 0) {
            primeFactors.add(2);
            number = number / 2;
        }

        int i = 3;
        int maxFactor = (int) Math.sqrt(number);
        while (i <= maxFactor) {
            while (number % i == 0) {
                primeFactors.add(i);
                number = number / i;
                maxFactor = (int) Math.sqrt(number);
            }
            i += 2;
        }
        if (number > 1) {
            primeFactors.add(number);
        }

        return primeFactors;
    }

    /**
     * find prime factors without check duplicates and even fields O(N)
     */
    public static List<Integer> findPrimeFactorsSlower(int number) {
        List<Integer> primeFactors = new ArrayList<>();
        for (int i = 2; i <= number; ++i) {
            while(number % i == 0) {
                number /= i;
                primeFactors.add(i);
            }
        }
        return primeFactors;
    }

    /**
     * find primes using sieve of Eratosthenes
     */
    public static List<Integer> findPrimes(int number) {
        boolean[] composite = new boolean[number + 1];

        for (int i = 4; i < composite.length; i += 2) {
            composite[i] = true;
        }
        int nextPrime = 3;
        int stopAt = (int) Math.sqrt(number);
        while (nextPrime <= stopAt) {
            for (int i = nextPrime * nextPrime; i <= number; i += nextPrime) {
                composite[i] = true;
            }
            nextPrime += 2;
            while (nextPrime < number && composite[nextPrime]) {
                nextPrime += 2;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (!composite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    /**
     * Check number using Fermat primality test
     */
    public static boolean isPrimeNumber(int number, int maxTests) {
        if (maxTests <= 0) {
            maxTests = 3;
        }
        for (int i = 0; i < maxTests; i++) {
            int n = (int) (Math.random() * number);
            double pow = Math.pow(n, number - 1);
            if (pow == POSITIVE_INFINITY) {
                return findPrimeFactors(number).size() == 1;
            }
            if (pow % number != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * The greatest common divisor of two integers is the largest integer that
     * evenly divides both of the numbers. Uses  Euclid’s algorithm.
     */
    public static int findGreatestCommonDivisor(int firstNumber, int secondNumber) {
        while (secondNumber > 0) {
            int remainder = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = remainder;
        }
        return firstNumber;
    }

    public static BigInteger performExponentiation(int value, int power) {
        if (power == 0) {
            return BigInteger.ONE;
        }
        if (power == 1) {
            return BigInteger.valueOf(value);
        }
        BigInteger result = BigInteger.valueOf(value);
        int pow = 2;
        while (true) {
            result = result.multiply(result);
            if (pow * 2 > power) {
                break;
            }
            pow *= 2;
        }
        pow = power - pow;

        return result.multiply(performExponentiation(value, pow));
    }

    public static int floorPowerOfTwo(int x) {
        x = x | (x >> 1);
        x = x | (x >> 2);
        x = x | (x >> 4);
        x = x | (x >> 8);
        x = x | (x >> 16);
        return x - (x >> 1);
    }

    public static int ceilPowerOfTwo(int x) {
        return floorPowerOfTwo(x) * 2;
    }

}
