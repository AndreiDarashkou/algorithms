package com.study.algorithm.numerical

import spock.lang.Specification
import spock.lang.Unroll

class NumericalAlgorithmsSpec extends Specification {

    @Unroll
    def "FindPrimeFactors"() {
        given:
        def factors = NumericalAlgorithms.findPrimeFactors(number)
        def factorsSlower = NumericalAlgorithms.findPrimeFactorsSlower(number)
        expect:
        factors.size() == size
        elements == factors
        factorsSlower.size() == size
        elements == factorsSlower
        where:
        number | size | elements
        1      | 0    | []
        2      | 1    | [2]
        4      | 2    | [2, 2]
        5      | 1    | [5]
        64     | 6    | [2, 2, 2, 2, 2, 2]
        455    | 3    | [5, 7, 13]
        123    | 2    | [3, 41]

    }

    @Unroll
    def "Test result equivalence between findPrimeFactors and findPrimeFactorsSlower methods"() {
        given:
        def number = (int) (Math.random() * mul)
        expect:
        NumericalAlgorithms.findPrimeFactorsSlower(number) == NumericalAlgorithms.findPrimeFactors(number)
        where:
        mul  || _
        100  || _
        100  || _
        100  || _
        1000 || _
        1000 || _
        1000 || _
    }


    @Unroll
    def "Test performExponentiation"() {
        expect:
        NumericalAlgorithms.performExponentiation(value, power) == result
        where:
        value | power || result
        2     | 2     || 4
        2     | 5     || 32
        5     | 3     || 125
        13    | 4     || 28561
        6     | 7     || 279936
        11    | 11    || 285311670611
        45    | 8     || 16815125390625
    }


}
