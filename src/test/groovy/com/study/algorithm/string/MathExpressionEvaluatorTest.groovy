package com.study.algorithm.string

import spock.lang.Specification
import spock.lang.Unroll

class MathExpressionEvaluatorTest extends Specification {

    @Unroll
    def "expression #expression should be equal #result"() {
        expect:
        MathExpressionEvaluator.calculate(expression) == resut
        where:
        expression                 | resut
        "(6 + -( -4))"             | 10.0
        "(6 + -(-4))"              | 10.0
        "6 + -4"                   | 2.0
        "6 + 4 - 8"                | 2.0
        "-6 + 4 - 8"               | -10.0
        "-6 + 4 - 8 * 2 + 8/4"     | -16.0
        "-6 + (4 - 8) * 2 + 8/4"   | -12.0
        "-6 + (4 - 8) * (2 + 8)/4" | -16.0
    }

}
