package com.study.algorithm.util

import spock.lang.Specification

class StringUtilsTest extends Specification {

    def "ReverseWords"() {
        expect:
        StringUtils.reverseWords(original) == resilt
        where:
        original                             || resilt
        "abc"                                || "cba"
        "abc def"                            || "cba fed"
        " mother  father    sister brother " || " rehtom  rehtaf    retsis rehtorb "
        "   "                                || "   "
    }

}
