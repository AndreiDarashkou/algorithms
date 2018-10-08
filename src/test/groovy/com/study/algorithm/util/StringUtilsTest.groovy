package com.study.algorithm.util

import spock.lang.Specification
import spock.lang.Unroll

class StringUtilsTest extends Specification {

    @Unroll
    def "ReverseWords"() {
        expect:
        StringUtils.reverseWords(original) == result
        where:
        original                             || result
        "abc"                                || "cba"
        "abc def"                            || "cba fed"
        " mother  father    sister brother " || " rehtom  rehtaf    retsis rehtorb "
        "   "                                || "   "
    }

    @Unroll
    def "formatDuration"() {
        expect:
        StringUtils.formatDuration(secons) == date
        where:
        secons || date
        1      || "1 second"
        62     || "1 minute and 2 seconds"
        345321 || "3 days, 23 hours, 55 minutes and 21 seconds"
    }

}
