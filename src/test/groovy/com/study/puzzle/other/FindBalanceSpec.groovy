package com.study.puzzle.other

import spock.lang.Specification
import spock.lang.Unroll

class FindBalanceSpec extends Specification {

    @Unroll
    def "should find pivot #result with given #arr"() {
        expect:
        FindBalance.find((int[]) arr) == result
        where:
        arr                             | result
        [1, 2, 3, 4, 5]                 | 3
        [1, 2, 3, 4, 5, -1]             | 2
        [3, 1, 2, 4, 3]                 | 1
        [-1, 1, -1, 1, -1, 0, 1, 1, -1] | 0
        [1, 1, 1, -1, -1, -1]           | 2
        [7, -1, 1, 1, 1, 1, 1, 1]       | 0
        [5, 1, 2, 1, 1]                 | 0
        [-5, -1, -2, -1, -1]            | 0
    }
}
