package com.study.data_structure.tree

import spock.lang.Specification
import spock.lang.Unroll

class AVLTreeTest extends Specification {

    @Unroll
    def "Add"() {
        given:
        Tree<Integer> tree = new AVLTree<>()
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
        Tree<Integer> tree = new AVLTree<>()
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.remove(remove) == result
        tree.find(remove) == null
        where:
        values                            | remove || result
        [100, 45, 78, 23, 27, 21, 88, 46] | 23     || true
        [100, 45, 78, 23, 27, 21, 88, 46] | 25     || false
        [40, 45, 78, 23, 27, 21, 88, 45]  | 45     || true
        [40, 45, 78, 23, 27, 21, 88, 45]  | 0      || false
        [40, 45, 78, 23, 27, 21, 88, 45]  | null   || false
        [40, 45, 78, 23, 27, 21, 88, 45]  | 40     || true
    }

    @Unroll
    def "InorderTraverse"() {
        given:
        Tree<Integer> tree = new AVLTree<>()
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.inorderTraverse() == result
        where:
        values                                   || result
        [100, 45, 78, 23, 27, 21, 88, 46]        || [21, 23, 27, 45, 46, 78, 88, 100]
        [67, 27, 69, 35, 13, 19, 25, 89, 58, 99] || [13, 19, 25, 27, 35, 58, 67, 69, 89, 99]
    }

    @Unroll
    def "BreadthFirstTraverse"() {
        given:
        Tree<Integer> tree = new AVLTree<>()
        when:
        (values).each {
            tree.add(it)
        }
        then:
        tree.breadthFirstTraverse() == result
        where:
        values                                   || result
        [100, 45, 78, 23, 27, 21, 88, 46]        || [45, 23, 100, 21, 27, 78, 46, 88]
        [67, 27, 69, 35, 13, 19, 25, 89, 58, 99] || [27, 13, 67, 19, 35, 69, 25, 58, 89, 99]
    }
}
