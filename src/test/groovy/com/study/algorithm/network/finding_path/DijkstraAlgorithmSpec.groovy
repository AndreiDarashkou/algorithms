package com.study.algorithm.network.finding_path

import spock.lang.Specification
import spock.lang.Unroll

import java.awt.*;

class DijkstraAlgorithmSpec extends Specification {

    @Unroll
    def "FindPath"() {
        given:
        def initGrid = grid as int[][]
        def size = initGrid.length
        expect:
        DijkstraAlgorithm.findLowestCostPath(initGrid, from, to).collect { p -> (p.x * size + p.y) as int }.toList() == path
        where:
        grid                 | from            | to              | path
        [[1, 0, 0, 0, 1, 1],
         [1, 1, 1, 0, 0, 1],
         [0, 1, 1, 1, 0, 1],
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
    }

}
