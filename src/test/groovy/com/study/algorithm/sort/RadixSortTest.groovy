package com.study.algorithm.sort

import spock.lang.Specification
import spock.lang.Unroll


class RadixSortTest extends Specification {

    @Unroll
    def "test radix sort method"() {
        given:
        def array = elements as int[]
        when:
        RadixSort.sort(array)
        then:
        array == result.toArray()
        where:
        elements                                                                                     | result
        [101, 55, 104, 8, 73, 113, 13, 17, 109, 39]                                                  | [8, 13, 17, 39, 55, 73, 101, 104, 109, 113]
        [47, 182, 93, 172, 103, 171, 15, 88, 114, 141, 123, 105, 1, 140, 10, 196, 162, 76, 145, 139] | [1, 10, 15, 47, 76, 88, 93, 103, 105, 114, 123, 139, 140, 141, 145, 162, 171, 172, 182, 196]
    }

    @Unroll
    def "test radix sort base method"() {
        given:
        def array = elements as int[]
        when:
        RadixSort.bucketSort(array, base)
        then:
        array == result.toArray()
        where:
        elements                                    | base | result
        [101, 55, 104, 8, 73, 113, 13, 17, 109, 39] | 10   | [8, 101, 104, 109, 13, 17, 113, 39, 55, 73]
        [101, 55, 1114, 73, 113, 13, 17, 1089, 39]  | 100  | [13, 17, 39, 55, 73, 1089, 101, 113, 1114]

    }

}
