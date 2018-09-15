package com.study.algorithm.util

import spock.lang.Specification

class MatrixUtilsTest extends Specification {

    def "check suitability of matrices"() {
        expect:
        MatrixUtils.mul(mat1, mat2).length == resMat.length
        MatrixUtils.mul(mat1, mat2)[0].length == resMat[0].length
        where:
        mat1           | mat2           | resMat
        new int[1][1]  | new int[1][1]  | new int[1][1]
        new int[2][2]  | new int[2][2]  | new int[2][2]
        new int[2][3]  | new int[3][2]  | new int[2][2]
        new int[2][3]  | new int[3][1]  | new int[2][1]
        new int[3][5]  | new int[5][4]  | new int[3][4]
        new int[8][13] | new int[13][5] | new int[8][5]
    }

    def "check mul result"() {
        expect:
        MatrixUtils.mul(mat1 as int[][], mat2 as int[][]) == resMat as int[][]
        where:
        mat1             | mat2             | resMat
        [[1, 1], [1, 1]] | [[1, 1], [1, 1]] | [[2, 2], [2, 2]]
        [[1, 2], [3, 4]] | [[6, 7], [3, 2]] | [[12, 11], [30, 29]]
    }

}
