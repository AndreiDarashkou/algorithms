package com.study.algorithm.string

import spock.lang.Specification
import spock.lang.Unroll

class SearchSubstringTest extends Specification {

    @Unroll
    def "Search"() {
        expect:
        SearchSubstring.search(text, subtext) == resut
        where:
        text                                                                                                       | subtext       | resut
        "mother father sister brother"                                                                             | "sister"      | 14
        "mother father sister brother"                                                                             | "uncle"       | -1
        "abra cadarbra bra bra bra"                                                                                | "arb"         | 8
        "abra cadarbra bra bra bra"                                                                                | "bra "        | 1
        "abba daba abadabracadabra"                                                                                | "cadabra"     | 18
        "It’s a fairly common task for a Java developer to convert a list to an array or from an array to a list." | "to an array" | 65
    }

    @Unroll
    def "SearchBoyerMoore"() {
        expect:
        SearchSubstring.searchBoyerMoore(text, subtext) == resut
        where:
        text                                                                                                       | subtext       | resut
        "mother father sister brother"                                                                             | "sister"      | 14
        "mother father sister brother"                                                                             | "uncle"       | -1
        "abra cadarbra bra bra bra"                                                                                | "arb"         | 8
        "abra cadarbra bra bra bra"                                                                                | "bra "        | 1
        "abba daba abadabracadabra"                                                                                | "cadabra"     | 18
        "It’s a fairly common task for a Java developer to convert a list to an array or from an array to a list." | "to an array" | 65
    }

}
