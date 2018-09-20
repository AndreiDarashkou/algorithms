package com.study.algorithm.backtracking

import spock.lang.Specification
import spock.lang.Unroll

class EightQueensProblemTest extends Specification {

    def "FindResolution"() {
        expect:
        EightQueensProblem.findSolution() == [[true, false, false, false, false, false, false, false],
                                              [false, false, false, false, true,  false, false, false],
                                              [false, false, false, false, false, false, false, true],
                                              [false, false, false, false, false, true,  false, false],
                                              [false, false, true,  false, false, false, false, false],
                                              [false, false, false, false, false, false, true,  false],
                                              [false, true,  false, false, false, false, false, false],
                                              [false, false, false, true,  false, false, false, false]] as boolean[][]
    }

    @Unroll
    def "IsNotCorrect"() {
        expect:
        EightQueensProblem.isInvalid(chessBoard as boolean[][]) == result
        where:
        chessBoard                                                 | result
        [[true, false, false, false, false, false, false, false],
         [false, false, true, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [true, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false]] | true

        [[true, false, false, false, false, false, false, false],
         [false, false, true, false, false, false, false, false],
         [false, false, false, false, true, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [true, false, false, false, false, false, false, false]]  | true

        [[true, false, false, false, false, false, false, false],
         [false, false, true, false, false, false, false, false],
         [false, false, false, false, true, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, true]]  | true

        [[true, false, false, false, false, false, false, false],
         [false, false, true, false, false, false, false, false],
         [false, false, false, false, true, false, false, false],
         [false, false, false, false, false, false, true, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false],
         [false, false, false, false, false, false, false, false]] | false
    }

}
