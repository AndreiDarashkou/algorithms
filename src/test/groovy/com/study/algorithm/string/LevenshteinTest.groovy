package com.study.algorithm.string

import spock.lang.Specification
import spock.lang.Unroll

class LevenshteinTest extends Specification {

    @Unroll
    def "the Levenshtein distance should be equal to #distance where words #first and #second"() {
        expect:
        Levenshtein.levenshteinDistance(first, second) == distance
        where:
        first                 | second                | distance
        "mother"              | "father"              | 2
        "abcd"                | "acd"                 | 1
        "there is a good day" | "there is a nice cat" | 6
        "ABBCEF"              | "ABCDF"               | 2
    }
}
