package com.study.algorithm.sort

import com.study.algorithm.sort.BubbleSort
import spock.lang.Specification
import spock.lang.Unroll


class ShellSortTest extends Specification {

    @Unroll
    def "test bubble sort method"() {
        given:
        def array = elements as int[]
        when:
        ShellSort.sort(array)
        then:
        array == result.toArray()
        where:
        elements                       | result
        [1, 5, 4]                      | [1, 4, 5]
        [11, 5, 4, 6, 12, 99]          | [4, 5, 6, 11, 12, 99]
        [1, 5, -4]                     | [-4, 1, 5]
        [77]                           | [77]
        []                             | []
        [3, 2, 1, 1, 1]                | [1, 1, 1, 2, 3]
        [3, 2, 1, 1, 1, 7, 8, 9, 2, 1] | [1, 1, 1, 1, 2, 2, 3, 7, 8, 9]
    }


    @Unroll
    def "test incSedgewick method"() {
        expect:
        ShellSort.incSedgewick(arraySize) == result
        where:
        arraySize || result
        5         || [1, 6]
        10        || [1, 6]
        15        || [1, 6]
        50        || [1, 6, 16, 47]
        400       || [1, 6, 16, 47, 95, 238]
        100000    || [1, 6, 16, 47, 95, 238, 445, 1051, 1913, 4405, 7921, 18025, 32225, 72914]
    }
}
