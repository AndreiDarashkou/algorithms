package com.study.puzzle.other

import spock.lang.Specification
import spock.lang.Unroll

class MessageValidatorSpec extends Specification {

    @Unroll
    def "should validate string #message with result #result"() {
        expect:
        MessageValidator.isAValidMessage(message) == result
        where:
        message                | result
        "5hello2hi3two"        | true
        "3hey5hello2hi"        | true
        ""                     | true
        "4code13hellocodewars" | true
        "3andrew"              | false
        "0"                    | true
        "code4hello5"          | false
    }
}