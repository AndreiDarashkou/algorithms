package com.study.algorithm.network.finding_path

import spock.lang.Specification
import spock.lang.Unroll

import java.awt.*
import java.util.List

class FloydWarshallAlgorithmTest extends Specification {

    @Unroll
    def "FindPath"() {
        expect:
        FloydWarshallAlgorithm.findPath(grid as Integer[][], from, to) == path as List
        where:
        grid                 | from                  | to                   | path
        [[1, 0, 0, 0, 1, 1],
         [1, 1, 1, 0, 0, 1],
         [0, 1, 1, 0, 0, 1],
         [0, 0, 0, 1, 0, 1],
         [0, 0, 0, 0, 0, 0],
         [1, 0, 1, 1, 1, 1]] | new Point(0, 1) | new Point(5, 1) | [2, 3, 9, 10, 16, 22, 28, 27, 26, 25, 31]

        [[1, 1, 1, 0, 1, 1],
         [1, 0, 0, 0, 0, 1],
         [0, 0, 1, 0, 0, 1],
         [0, 0, 0, 1, 0, 1],
         [0, 0, 0, 0, 1, 0],
         [1, 0, 1, 1, 1, 1]] | new Point(0, 3) | new Point(5, 1) | [9, 8, 7, 13, 19, 25, 31]

        [[1, 1, 1, 0, 1, 1],
         [1, 0, 0, 1, 0, 1],
         [0, 0, 1, 0, 0, 1],
         [0, 0, 0, 1, 0, 1],
         [0, 0, 0, 0, 1, 0],
         [1, 0, 1, 1, 1, 1]] | new Point(0, 3) | new Point(5, 1) | []

        [[1, 0, 1, 0, 1, 1],
         [1, 0, 0, 1, 0, 0],
         [0, 0, 1, 0, 0, 1],
         [0, 1, 0, 0, 0, 1],
         [0, 0, 0, 0, 1, 0],
         [1, 0, 1, 1, 1, 1]] | new Point(0, 1) | new Point(1, 5) | [7, 13, 12, 18, 24, 25, 26, 20, 21, 15, 16, 10, 11]
    }

}
