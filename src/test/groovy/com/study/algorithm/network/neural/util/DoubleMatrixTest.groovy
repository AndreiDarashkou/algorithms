package com.study.algorithm.network.neural.util

import spock.lang.Specification
import spock.lang.Unroll

class DoubleMatrixTest extends Specification {

    @Unroll
    def "sub"() {
        given:
        def matrix = new DoubleMatrix(rows, cols)
        for (int i = 0; i < matrix.length; i++) {
            matrix.put(i, data)
        }
        def subMatrix = new DoubleMatrix(rows, cols)
        for (int i = 0; i < subMatrix.length; i++) {
            subMatrix.put(i, sub)
        }
        when:
        def resultMatrix = matrix.sub(subMatrix)
        then:
        for (int i = 0; i < matrix.length; i++) {
            assert resultMatrix.get(i) == result
        }
        where:
        rows | cols | data | sub  | result
        1    | 1    | 2    | 1.5d | 0.5d
        3    | 4    | 5    | 2.0d | 3.0d
    }

    @Unroll
    def "addi"() {
        given:
        def matrix = new DoubleMatrix(rows, cols)
        for (int i = 0; i < matrix.length; i++) {
            matrix.put(i, data)
        }
        def addMatrix = new DoubleMatrix(rows, cols)
        for (int i = 0; i < addMatrix.length; i++) {
            addMatrix.put(i, add)
        }
        when:
        matrix.addi(addMatrix)
        then:
        for (int i = 0; i < matrix.length; i++) {
            assert matrix.get(i) == result
        }
        where:
        rows | cols | data | add  | result
        1    | 1    | 2    | 1.5d | 3.5d
        3    | 4    | 5    | 2.0d | 7.0d
    }

    @Unroll
    def "mmul"() {
        given:
        def matrix = new DoubleMatrix(rows, cols)
        matrix.data = data as double[]
        def mulMatrix = new DoubleMatrix(cols, rows)
        mulMatrix.data = mul as double[]
        when:
        def resMatrix = matrix.mmul(mulMatrix)
        then:
        for (int i = 0; i < resMatrix.length; i++) {
            assert resMatrix.get(i) == (result as double[])[i]
        }
        where:
        rows | cols | data                           | mul                            | result
        2    | 3    | [1.0, 2.0, 3.0, 4.0, 5.0, 6.0] | [2.0, 4.0, 1.0, 6.0, 8.0, 3.0] | [28.0, 25.0, 61.0, 64.0]
        2    | 2    | [2.0, 6.0, 1.0, 3.0]           | [4.0, 5.0, 7.0, 8.0]           | [50.0, 58.0, 25.0, 29.0]
    }

    @Unroll
    def "transpose"() {
        given:
        def matrix = new DoubleMatrix(rows, cols)
        matrix.data = data as double[]
        when:
        def resMatrix = matrix.transpose()
        then:
        for (int i = 0; i < resMatrix.length; i++) {
            assert resMatrix.get(i) == (result as double[])[i]
        }
        where:
        rows | cols | data                           | result
        2    | 3    | [1.0, 2.0, 3.0, 4.0, 5.0, 6.0] | [1.0, 4.0, 2.0, 5.0, 3.0, 6.0]
        2    | 2    | [2.0, 6.0, 1.0, 3.0]           | [2.0, 1.0, 6.0, 3.0]
    }

}