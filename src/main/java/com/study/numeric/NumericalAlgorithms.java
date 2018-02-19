package com.study.numeric;

import java.util.ArrayList;
import java.util.List;

public final class NumericalAlgorithms {

    private NumericalAlgorithms() {
    }

    /**
     * нахождение простых множителей числа
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
     * нахождение простых чисел (решето Эратосфена, sieve of Eratosthenes)
     */
    public static List<Integer> findPrimes(int number) {
        boolean[] composite = new boolean[number + 1];

        for (int i = 4; i < composite.length; i += 2) {
            composite[i] = true;
        }
        int nextPrime = 3;
        int stopAt = (int) Math.sqrt(number);
        while (nextPrime < stopAt) {
            for (int i = nextPrime * 2; i < number; i += nextPrime) {
                composite[i] = true;
            }
            nextPrime += 2;
            while (nextPrime < number && composite[nextPrime]) {
                nextPrime += 2;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 1; i < number; i++) {
            if (!composite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static boolean isPrimeNumber(int number) {
        for (int i = 0; i < 5; i++) {
            int n = (int) (Math.random() * number);
            if (Math.pow(n, number - 1) % number != 1) {
                return false;
            }
        }
        return true;
    }

}
