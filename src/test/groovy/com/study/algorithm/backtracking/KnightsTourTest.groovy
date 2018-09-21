package com.study.algorithm.backtracking

import spock.lang.Specification

class KnightsTourTest extends Specification {

    def "FindWay"() {
        given:
        def tour = new KnightsTour(size)
        when:
        int[][] result = tour.findWay();
        then:
        result != null
        result.length == size
        result[0].length == size

        result.each {
            result[0].each {
                assert 0 != it
            }
        }
        where:
        size | _
        5    | _
        8    | _
        10   | _

    }
}
