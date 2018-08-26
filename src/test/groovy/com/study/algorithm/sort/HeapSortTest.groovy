package com.study.algorithm.sort

import spock.lang.Specification
import spock.lang.Unroll


class HeapSortTest extends Specification {


    @Unroll
    def "test Heap sort method"() {
        given:
        def array = elements as int[]
        when:
        HeapSort.sort(array)
        then:
        array == result.toArray()
        where:
        elements                                    | result
        [1, 5, 4]                                   | [1, 4, 5]
        [11, 5, 4, 6, 12, 99]                       | [4, 5, 6, 11, 12, 99]
        [1, 5, -4]                                  | [-4, 1, 5]
        [77]                                        | [77]
        []                                          | []
        [3, 2, 1, 1, 1]                             | [1, 1, 1, 2, 3]
        [3, 2, 1, 1, 1, 7, 8, 9, 2, 1]              | [1, 1, 1, 1, 2, 2, 3, 7, 8, 9]
        [-1, -7, -90, -5, -23, -23, -1, -7, -7, -5] | [-90, -23, -23, -7, -7, -7, -5, -5, -1, -1]
    }

    @Unroll
    def "test removeTopElement method"() {
        given:
        def array = elements as int[]
        when:
        HeapSort.removeTopElement(array, array.length - 1)
        then:
        array == result.toArray()
        where:
        elements                                    | result
        [5, 1, 4]                                   | [4, 1, 4]
        [99, 11, 12, 5, 6, 4]                       | [12, 11, 4, 5, 6, 4]
        [5, 1, -4]                                  | [1, -4, -4]
        [77]                                        | [77]
        [3, 2, 1, 1, 1]                             | [2, 1, 1, 1, 1]
        [9, 8, 7, 2, 1, 1, 3, 1, 2, 1]              | [8, 2, 7, 2, 1, 1, 3, 1, 1, 1]
        [-1, -5, -1, -7, -5, -90, -23, -7, -7, -23] | [-1, -5, -23, -7, -5, -90, -23, -7, -7, -23]
    }


    @Unroll
    def "test make heap method"() {
        given:
        def array = elements as int[]
        when:
        HeapSort.makeHeap(array)
        then:
        array == result.toArray()
        where:
        elements                                    | result
        [1, 5, 4]                                   | [5, 1, 4]
        [11, 5, 4, 6, 12, 99]                       | [99, 11, 12, 5, 6, 4]
        [1, 5, -4]                                  | [5, 1, -4]
        [77]                                        | [77]
        []                                          | []
        [3, 2, 1, 1, 1]                             | [3, 2, 1, 1, 1]
        [3, 2, 1, 1, 1, 7, 8, 9, 2, 1]              | [9, 8, 7, 2, 1, 1, 3, 1, 2, 1]
        [-1, -7, -90, -5, -23, -23, -1, -7, -7, -5] | [-1, -5, -1, -7, -5, -90, -23, -7, -7, -23]
    }

}
