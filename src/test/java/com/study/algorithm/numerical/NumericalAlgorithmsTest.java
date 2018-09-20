package com.study.algorithm.numerical;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for numerical algorithms")
class NumericalAlgorithmsTest {

    private static final int MAX_TESTS = 10;

    @Test
    @DisplayName("Test find prime factors for number 12")
    void testFindPrimeFactorsFor12() {
        List<Integer> result = NumericalAlgorithms.findPrimeFactors(12);
        assertEquals(3, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    @DisplayName("Test find prime factors for number 55")
    void testFindPrimeFactorsFor55() {
        List<Integer> result = NumericalAlgorithms.findPrimeFactors(55);
        assertEquals(2, result.size());
        assertTrue(result.contains(5));
        assertTrue(result.contains(11));
    }

    @Test
    @DisplayName("Test find prime factors for number 567")
    void testFindPrimeFactorsFor567() {
        List<Integer> result = NumericalAlgorithms.findPrimeFactors(567);
        assertEquals(5, result.size());
        assertTrue(result.contains(3));
        assertTrue(result.contains(7));
    }

    @Test
    @DisplayName("Test find primes for number 3")
    void testFindPrimesFor3() {
        List<Integer> result = NumericalAlgorithms.findPrimes(3);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    @DisplayName("Test find primes for number 3")
    void testFindPrimesFor9() {
        List<Integer> result = NumericalAlgorithms.findPrimes(11);
        assertEquals(6, result.size());
        assertTrue(result.contains(5));
        assertTrue(result.contains(7));
        assertTrue(result.contains(11));
    }

    @Test
    @DisplayName("Test find primes for number 22")
    void testFindPrimesFor22() {
        List<Integer> result = NumericalAlgorithms.findPrimes(22);
        assertEquals(9, result.size());
        assertTrue(result.contains(7));
        assertTrue(result.contains(11));
        assertTrue(result.contains(19));
        assertFalse(result.contains(9));
        assertFalse(result.contains(15));
        assertFalse(result.contains(20));
    }

    @Test
    @DisplayName("Test find primes for number 567")
    void testFindPrimesFor567() {
        List<Integer> result = NumericalAlgorithms.findPrimes(567);
        assertEquals(104, result.size());
        assertTrue(result.contains(193));
        assertTrue(result.contains(241));
        assertTrue(result.contains(337));
        assertTrue(result.contains(521));
        assertFalse(result.contains(213));
        assertFalse(result.contains(555));
        assertFalse(result.contains(543));
    }

    @Test
    @DisplayName("Test find primes for number 78765")
    void testFindPrimesFor78765() {
        List<Integer> result = NumericalAlgorithms.findPrimes(78765);
        assertEquals(7725, result.size());
        assertTrue(result.contains(2731));
        assertTrue(result.contains(727));
        assertTrue(result.contains(1999));
        assertTrue(result.contains(70657));
    }

    @Test
    @DisplayName("Check prime number 99")
    void checkPrimeNumber99() {
        assertFalse(NumericalAlgorithms.isPrimeNumber(99, MAX_TESTS));
    }

    @Test
    @DisplayName("Check prime number 36504")
    void checkPrimeNumber36504() {
        assertFalse(NumericalAlgorithms.isPrimeNumber(36504, MAX_TESTS));
    }

    @Test
    @DisplayName("Check prime number 2003")
    void checkPrimeNumber2003() {
        assertTrue(NumericalAlgorithms.isPrimeNumber(2003, MAX_TESTS));
    }

    @Test
    @DisplayName("Check prime number 533")
    void checkPrimeNumber533() {
        assertFalse(NumericalAlgorithms.isPrimeNumber(533, MAX_TESTS));
    }

    @Test
    @DisplayName("Check prime number 177")
    void checkPrimeNumber177() {
        assertFalse(NumericalAlgorithms.isPrimeNumber(177, MAX_TESTS));
    }

    @Test
    @DisplayName("Check is not prime number")
    void checkIsNotPrimeNumber6() {
        assertFalse(NumericalAlgorithms.isPrimeNumber(6, 0));
    }

    @Test
    @DisplayName("Find greatest common divisor for numbers 64 and 96")
    void findGreatestCommonDivisorsFor64And96() {
        assertEquals(32, NumericalAlgorithms.findGreatestCommonDivisor(64, 96));
    }

    @Test
    @DisplayName("Find greatest common divisor for numbers 555 and 1005")
    void findGreatestCommonDivisorsFor555And1005() {
        assertEquals(15, NumericalAlgorithms.findGreatestCommonDivisor(555, 1005));
    }

    @Test
    @DisplayName("Find greatest common divisor for numbers 2003 and 1999")
    void findGreatestCommonDivisorsFor2003And1005() {
        assertEquals(1, NumericalAlgorithms.findGreatestCommonDivisor(2003, 1999));
    }

}
