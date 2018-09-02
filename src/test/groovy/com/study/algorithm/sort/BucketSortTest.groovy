package com.study.algorithm.sort

import spock.lang.Specification
import spock.lang.Unroll


class BucketSortTest extends Specification {

    @Unroll
    def "test bucket sort method"() {
        given:
        def array = elements as int[]
        when:
        BucketSort.sort(array)
        then:
        array == result.toArray()
        where:
        elements                                    | result
        [1, 5, 4]                                   | [1, 4, 5]
        [11, 5, 4, 6, 12, 8, 99, 3]                 | [3, 4, 5, 6, 8, 11, 12, 99]
        [1, 5, -4]                                  | [-4, 1, 5]
        [77]                                        | [77]
        []                                          | []
        [3, 2, 1, 1, 1]                             | [1, 1, 1, 2, 3]
        [3, 2, 1, 1, 1, 7, 8, 9, 2, 1]              | [1, 1, 1, 1, 2, 2, 3, 7, 8, 9]
        [-1, -7, -90, -5, -23, -23, -1, -7, -7, -5] | [-90, -23, -23, -7, -7, -7, -5, -5, -1, -1]
    }

}
