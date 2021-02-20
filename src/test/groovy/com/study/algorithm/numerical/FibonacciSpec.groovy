package com.study.algorithm.numerical

import spock.lang.Specification
import spock.lang.Unroll

class FibonacciSpec extends Specification {

    @Unroll
    def "calcRecursive"() {
        expect:
        Fibonacci.calcRecursive(number) == result
        where:
        number | result
        0      | 0
        1      | 1
        5      | 5
        10     | 55
        40     | 102334155
    }

    @Unroll
    def "calcRecursiveWithoutDuplicates"() {
        expect:
        Fibonacci.calcRecursiveWithoutDuplicates(number) == result
        where:
        number | result
        0      | 0
        1      | 1
        5      | 5
        10     | 55
        40     | 102334155
        44     | 701408733
        50     | 12586269025
        80     | 23416728348467685
        100    | 354224848179261915075
        200    | 280571172992510140037611932413038677189525
        400    | 176023680645013966468226945392411250770384383304492191886725992896575345044216019675
    }

    @Unroll
    def "calcWithoutRecursive"() {
        expect:
        Fibonacci.calcWithoutRecursive(number) == result
        where:
        number | result
        0      | 0
        1      | 1
        2      | 1
        3      | 2
        4      | 3
        5      | 5
        -1     | 1
        -2     | -1
        -3     | 2
        -4     | -3
        -5     | 5
        -45    | 1134903170
        10     | 55
        40     | 102334155
        44     | 701408733
        50     | 12586269025
        80     | 23416728348467685
        100    | 354224848179261915075
        200    | 280571172992510140037611932413038677189525
        400    | 176023680645013966468226945392411250770384383304492191886725992896575345044216019675
    }

    @Unroll
    def "calc using matrices"() {
        expect:
        Fibonacci.calcUsingMatrix(number) == result
        where:
        number  | result
        0      | 0
        1      | 1
        2      | 1
        3      | 2
        4      | 3
        5      | 5
        -1     | 1
        -2     | -1
        -3     | 2
        -4     | -3
        -5     | 5
        -45    | 1134903170
        10     | 55
        40     | 102334155
        44     | 701408733
        50     | 12586269025
        80     | 23416728348467685
        100    | 354224848179261915075
        200    | 280571172992510140037611932413038677189525
        400    | 176023680645013966468226945392411250770384383304492191886725992896575345044216019675
    }

}
