package com.study.algorithm.search

import spock.lang.Specification
import spock.lang.Unroll


class InterpolationSearchTest extends Specification {

    @Unroll
    def "test find interpolation middle"() {
        expect:
        InterpolationSearch.middle(elements as int[], min, max, element) == middle
        where:
        elements                                   | min | max | element || middle
        [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]            | 0   | 9   | 7       || 6
        [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]   | 0   | 9   | 11      || 1
        [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]   | 2   | 6   | 13      || 3
        [20, 30, 40, 50, 60, 70, 90, 110]          | 0   | 7   | 90      || 5
        [3, 8, 13, 14, 15, 19, 28, 29, 31, 40, 49] | 4   | 10  | 28      || 6
    }

    @Unroll
    def "test interpolation search"() {
        expect:
        InterpolationSearch.search(elements as int[], element) == position
        where:
        elements                                        | element || position
        [3, 8, 13, 14, 15, 19, 28, 29, 31, 40, 49]      | 40      || 9
        [1, 7, 7, 8, 10, 22, 35, 66, 89, 113, 114]      | 114     || 10
        [1, 7, 7, 8, 10, 22, 35, 66, 89, 113, 114]      | 9       || -1
        [5, 12, 278, 451, 452, 453, 589, 631, 632, 999] | 999     || 9
    }
}
