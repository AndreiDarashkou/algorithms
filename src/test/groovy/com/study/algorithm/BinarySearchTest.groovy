package com.study.algorithm

import spock.lang.Specification
import spock.lang.Unroll


class BinarySearchTest extends Specification {

    @Unroll
    def "test findElementPositionRecursive"() {
        expect:
        BinarySearch.findElementPositionRecursive(elements as int[], element) == position
        where:
        elements                         | element || position
        [1, 2, 3]                        | 1       || 0
        [0, 8, 10, 12, 33]               | 12      || 3
        [2, 3, 5, 9]                     | 9       || 3
        [3]                              | 1       || null
        [5, 8, 16, 78]                   | 44      || null
        [1, 2, 3]                        | 2       || 1
        [1, 1, 1, 2, 3]                  | 1       || 0
        []                               | 2       || null
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 89      || 8
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 3       || 0
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 115     || null
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | -15     || null
    }

    @Unroll
    def "test findElementPosition"() {
        expect:
        BinarySearch.findElementPosition(elements as int[], element) == position
        where:
        elements                         | element | position
        [1, 2, 3]                        | 1      || 0
        [0, 8, 10, 12, 33]               | 12     || 3
        [2, 3, 5, 9]                     | 9      || 3
        [3]                              | 1      || null
        [5, 8, 16, 78]                   | 44     || null
        [1, 2, 3]                        | 2      || 1
        [1, 1, 1, 2, 3]                  | 1      || 0
        []                               | 2      || null
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 89     || 8
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 3      || 0
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | 115    || null
        [3, 7, 7, 8, 10, 22, 35, 66, 89] | -15    || null
    }
}
