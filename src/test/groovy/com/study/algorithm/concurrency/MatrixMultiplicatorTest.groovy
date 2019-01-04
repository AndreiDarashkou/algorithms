package com.study.algorithm.concurrency

import com.study.algorithm.util.MatrixUtils
import spock.lang.Specification
import spock.lang.Unroll

class MatrixMultiplicatorTest extends Specification {


    @Unroll
    def "check suitability of matrices"() {
        expect:
        MatrixMultiplicator.mul(mat1, mat2).length == resMat.length
        MatrixMultiplicator.mul(mat1, mat2)[0].length == resMat[0].length
        where:
        mat1           | mat2           | resMat
        new int[1][1]  | new int[1][1]  | new int[1][1]
        new int[2][2]  | new int[2][2]  | new int[2][2]
        new int[2][3]  | new int[3][2]  | new int[2][2]
        new int[2][3]  | new int[3][1]  | new int[2][1]
        new int[3][5]  | new int[5][4]  | new int[3][4]
        new int[8][13] | new int[13][5] | new int[8][5]
    }

    @Unroll
    def "check mul result"() {
        expect:
        MatrixMultiplicator.mul(mat1 as int[][], mat2 as int[][]) as int[][] == resMat as int[][]
        where:
        mat1                        | mat2                    | resMat
        [[1, 1], [1, 1]]            | [[1, 1], [1, 1]]        | [[2, 2], [2, 2]]
        [[1, 2], [3, 4]]            | [[6, 7], [3, 2]]        | [[12, 11], [30, 29]]
        [[2, 6], [1, 3]]            | [[4, 5], [7, 8]]        | [[50, 58], [25, 29]]
        [[6, 3, 8, 9, 0, 0, 7, 6,],
         [8, 6, 6, 0, 5, 9, 4, 0,],
         [7, 1, 2, 0, 1, 4, 3, 8,],
         [5, 3, 8, 5, 6, 2, 4, 9,],
         [8, 6, 6, 7, 9, 6, 9, 8,],
         [2, 3, 3, 1, 4, 8, 6, 5,],
         [9, 0, 2, 8, 2, 4, 5, 2,],
         [9, 6, 6, 7, 5, 8, 7, 9,],
         [9, 3, 6, 6, 4, 4, 6, 3,],
         [9, 3, 9, 8, 2, 5, 8, 5,]] | [[8, 6, 9, 4, 4, 2, 0,],
                                       [9, 2, 0, 7, 3, 3, 7,],
                                       [6, 8, 6, 6, 7, 9, 2,],
                                       [8, 7, 1, 0, 6, 7, 7,],
                                       [7, 0, 7, 7, 0, 3, 8,],
                                       [5, 7, 5, 3, 7, 1, 0,],
                                       [0, 0, 5, 5, 1, 4, 0,],
                                       [1, 3, 5, 5, 9, 2, 9]] | [[201, 187, 176, 158, 204, 196, 154],
                                                                 [234, 171, 208, 192, 159, 128, 94],
                                                                 [112, 112, 157, 121, 148, 70, 91],
                                                                 [216, 176, 215, 202, 214, 180, 201],
                                                                 [311, 223, 293, 276, 257, 222, 247],
                                                                 [142, 120, 160, 154, 151, 101, 111],
                                                                 [184, 160, 170, 109, 149, 126, 94],
                                                                 [302, 246, 279, 253, 282, 208, 224],
                                                                 [234, 187, 216, 178, 184, 169, 134],
                                                                 [261, 238, 247, 205, 244, 217, 156]]
    }

    @Unroll
    def "check mul result rand"() {
        given:
        int[][] matrix1 = MatrixUtils.generateRandom(rows, cols, 1, 9);
        int[][] matrix2 = MatrixUtils.generateRandom(cols, rows, 1, 9);
        expect:
        MatrixMultiplicator.mul(matrix1, matrix2) as int[][] == MatrixUtils.mul(matrix1, matrix2) as int[][]
        where:
        rows | cols
        10   | 8
        100  | 100
        500  | 500
    }

}
