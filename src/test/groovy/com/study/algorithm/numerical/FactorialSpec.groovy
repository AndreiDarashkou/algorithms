package com.study.algorithm.numerical

import spock.lang.Specification
import spock.lang.Unroll

class FactorialSpec extends Specification {

    @Unroll
    def "CalcRecursive"() {
        expect:
        Factorial.calcRecursive(num as BigInteger) == result
        where:
        num | result
        1   | 1
        2   | 2
        3   | 6
        4   | 24
        5   | 120
        20  | 2432902008176640000
        30  | 265252859812191058636308480000000
    }

    @Unroll
    def "Calculate"() {
        expect:
        Factorial.calculate(num) == result
        where:
        num | result
        1   | 1
        2   | 2
        3   | 6
        4   | 24
        5   | 120
        20  | 2432902008176640000
        30  | 265252859812191058636308480000000
    }

    @Unroll
    def "calcUsingTreeMul"() {
        expect:
        Factorial.calcUsingTreeMul(num) == result
        where:
        num | result
        1   | 1
        2   | 2
        3   | 6
        4   | 24
        5   | 120
        20  | 2432902008176640000
        30  | 265252859812191058636308480000000
    }

    @Unroll
    def "caclUsingPrimeFactorization"() {
        expect:
        Factorial.caclUsingPrimeFactorization(num) == result
        where:
        num | result
        1   | 1
        2   | 2
        3   | 6
        4   | 24
        5   | 120
        10  | 3628800
        20  | 2432902008176640000
        30  | 265252859812191058636308480000000
    }
}