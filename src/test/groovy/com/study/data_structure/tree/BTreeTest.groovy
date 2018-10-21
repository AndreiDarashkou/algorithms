package com.study.data_structure.tree

import spock.lang.Specification
import spock.lang.Unroll

class BTreeTest extends Specification {

    @Unroll
    def "Add"() {
        given:
        Tree<Integer> tree = new BTree<>(2)
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.find(find) == result
        where:
        values                            | find || result
        [100, 45, 78, 23, 27, 21, 88, 46] | 23   || 23
        [100, 45, 78, 23, 27, 21, 88, 46] | 25   || null
        [40, 45, 78, 23, 27, 21, 88, 45]  | 45   || 45
        [40, 45, 78, 23, 27, 21, 88, 45]  | 0    || null
        [40, 45, 78, 23, 27, 21, 88, 45]  | null || null
    }

    @Unroll
    def "Remove"() {
        given:
        Tree<Integer> tree = new BTree<>(2)
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.remove(remove) == result
        tree.find(remove) == null
        where:
        values                                                                                  | remove || result
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 23     || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | -10    || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 123    || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 33     || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | -13    || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 12     || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 45     || true
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | -11    || false
        [100, 45, 78, 23, 27, 21, 88, 46, 56, 33, 24, 12, 11, 10, 6, 5, 4, 3, 2, -10, -13, 123] | 8      || false
        [40, 45, 78, 23, 27, 21, 88, 45]                                                        | null   || false
    }

    @Unroll
    def "InorderTraverse"() {
        given:
        Tree<Integer> tree = new BTree<>(2)
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.inorderTraverse() == result
        where:
        values                                              || result
        [100, 45, 78, 23, 27, 21, 88, 46, 4, 8, 13, 19, 69] || [4, 8, 13, 19, 21, 23, 27, 45, 46, 69, 78, 88, 100]
        [67, 27, 69, 35, 13, 19, 25, 89, 58, 99]            || [13, 19, 25, 27, 35, 58, 67, 69, 89, 99]
    }

    @Unroll
    def "BreadthFirstTraverse"() {
        given:
        Tree<Integer> tree = new BTree<>(2)
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.breadthFirstTraverse() == result
        where:
        values                                              || result
        [100, 45, 78, 23, 27, 21, 88, 46, 4, 8, 13, 19, 69] || [21, 45, 78, 4, 8, 13, 19, 23, 27, 46, 69, 88, 100]
        [67, 27, 69, 35, 13, 19, 25, 89, 58, 99]            || [35, 69, 13, 19, 25, 27, 58, 67, 89, 99]
    }
}
