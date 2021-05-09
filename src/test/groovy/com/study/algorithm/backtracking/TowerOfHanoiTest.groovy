package com.study.algorithm.backtracking

import spock.lang.Specification
import spock.lang.Unroll

class TowerOfHanoiTest extends Specification {

    @Unroll
    def "should move #discCount disks from the first peg to the last of hanoi tower"() {
        given:
        def fromPeg = new TowerOfHanoi.Peg("From")
        (discCount..1).each {
            fromPeg.push(it)
        }
        def toPeg = new TowerOfHanoi.Peg("To")
        def auxiliary = new TowerOfHanoi.Peg("Auxiliary")
        when:
        TowerOfHanoi.move(fromPeg, toPeg, auxiliary, discCount)
        then:
        fromPeg.disks.size() == 0
        auxiliary.disks.size() == 0
        toPeg.disks.size() == discCount
        toPeg.disks == result as Stack
        where:
        discCount || result
        3         || [3, 2, 1]
        4         || [4, 3, 2, 1]
        5         || [5, 4, 3, 2, 1]
        6         || [6, 5, 4, 3, 2, 1]
    }


}
