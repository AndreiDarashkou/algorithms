package com.study.algorithm.numerical

import spock.lang.Specification
import spock.lang.Unroll

class PartitionNumberSpec extends Specification {

    @Unroll
    def "should partition number #value by summand"() {
        expect:
        PartitionNumberSequences.partition(value) == result
        where:
        result                                                                     | value
        [[1, 1], [2]]                                                              | 2
        [[1, 1, 1], [1, 2], [3]]                                                   | 3
        [[1, 1, 1, 1], [1, 1, 2], [1, 3], [2, 2], [4]]                             | 4
        [[1, 1, 1, 1, 1], [1, 1, 1, 2], [1, 1, 3], [1, 2, 2], [1, 4], [2, 3], [5]] | 5
        [[1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 2], [1, 1, 1, 1, 3], [1, 1, 1, 2, 2],
         [1, 1, 1, 4], [1, 1, 2, 3], [1, 1, 5], [1, 2, 2, 2], [1, 2, 4], [1, 3, 3],
         [1, 6], [2, 2, 3], [2, 5], [3, 4], [7]]                                   | 7
    }

    @Unroll
    def "should partition number #value by summand and return available partitions #partitions"() {
        expect:
        PartitionNumberSequences.partition(value).size() == partitions
        where:
        partitions | value
        2          | 2
        3          | 3
        5          | 4
        7          | 5
        15         | 7
        204226     | 50
    }
}
