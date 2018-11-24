package com.study.algorithm.cryptography

import spock.lang.Specification
import spock.lang.Unroll

class TranspositionCipherTest extends Specification {

    @Unroll
    def "Encrypt"() {
        expect:
        TranspositionCipher.encrypt(message, key) == result
        where:
        message                                               | key     | result
        "very important and secret message"                   | "climb" | "emancmgyot esxrpndree r stsxvitae a"
        "please take a look at xxx"                           | "dog"   | "pa kao  xeea lktxxlste oaxx"
        "simple test sentence"                                | "adcb"  | "slesept tem tnciesen"
        "In the market-place at Goderville was a great crowd" | "crowd" | "Iekladlag dta-eGv aaox mtc re erxhrp oiw twxn eatelsrcx"
    }

    @Unroll
    def "Decrypt"() {
        expect:
        TranspositionCipher.decrypt(message, key) == result
        where:
        message                                                   | key     | result
        "slesept tem tnciesen"                                    | "adcb"  | "simple test sentence"
        "emancmgyot esxrpndree r stsxvitae a"                     | "climb" | "very important and secret messagexx"
        "pa kao  xeea lktxxlste oaxx"                             | "dog"   | "please take a look at xxxxx"
        "Iekladlag dta-eGv aaox mtc re erxhrp oiw twxn eatelsrcx" | "crowd" | "In the market-place at Goderville was a great crowdxxxx"
    }

    @Unroll
    def "mapping"() {
        expect:
        TranspositionCipher.mapping(key) == result as byte[]
        where:
        key     | result
        "abc"   | [0, 1, 2]
        "adcb"  | [0, 3, 2, 1]
        "climb" | [1, 3, 2, 4, 0]
        "dog"   | [0, 2, 1]
        "crowd" | [0, 3, 2, 4, 1]
    }

    @Unroll
    def "inverse"() {
        expect:
        TranspositionCipher.inverse(mapping as byte[]) == result as byte[]
        where:
        mapping         | result
        [0, 1, 2]       | [0, 1, 2]
        [0, 3, 2, 1]    | [0, 3, 2, 1]
        [1, 3, 2, 4, 0] | [4, 0, 2, 1, 3]
        [0, 2, 1]       | [0, 2, 1]
        [0, 3, 2, 4, 1] | [0, 4, 2, 1, 3]
    }
}
