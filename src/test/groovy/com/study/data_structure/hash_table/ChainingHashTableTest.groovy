package com.study.data_structure.hash_table

import spock.lang.Specification
import spock.lang.Unroll

class ChainingHashTableTest extends Specification {

    @Unroll
    def "Test put/get methods"() {
        given:
        def table = new ChainingHashTable<String, String>()
        when:
        table.put(key1, val1)
        table.put(key2, val2)
        table.put(key3, val3)
        table.put(key4, val4)
        table.put(key5, val5)
        then:
        table.size() == size
        table.get(key1) == key1Res
        table.get(key2) == key2Res
        println(table)
        where:
        key1  | val1   | key2   | val2   | key3   | val3  | key4    | val4    | key5   | val5   | size | key1Res | key2Res
        "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 4    | "One2"  | "One2"
        "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 2    | "Three" | "Two2"
        "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 3    | "One"   | "One"
    }

    @Unroll
    def "Test remove method"() {
        given:
        def table = new ChainingHashTable<String, String>()
        when:
        table.put(key1, val1)
        table.put(key2, val2)
        table.put(key3, val3)
        table.put(key4, val4)
        table.put(key5, val5)
        then:
        table.remove(key1) == key1Res
        table.get(key1) == null
        table.remove(key2) == key2Res
        table.get(key2) == null
        table.remove(nonExistent) == null
        table.size() == size
        println(table)
        where:
        key1  | val1   | key2   | val2   | key3   | val3  | key4    | val4    | key5   | val5   | size | key1Res | key2Res | nonExistent
        "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 3    | "One2"  | null    | "six"
        "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 0    | "Three" | "Two2"  | "seven"
        "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 1    | "One"   | "One"   | "one"
    }

    @Unroll
    def "Test load factor of hash table"() {
        given:
        def table = new ChainingHashTable<Integer, Integer>(initCapacity, loadFactor)
        when:
        for (int el : elements) {
            table.put(el, el * 2)
        }
        then:
        table.size() == size
        table.capacity() == capacity
        println(table)
        where:
        elements                                                                              | initCapacity | loadFactor | capacity | size
        [1, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41]                | 30           | 0.75       | 30       | 19
        [1, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41, 88, 99, 45]    | 10           | 0.75       | 40       | 21
        [9, 1, 77, 67, 66, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41] | 10           | 0.5        | 80       | 21
    }

    @Unroll
    def "Test ChainingHashTable allArgs constructor"() {
        given:
        def table
        when:
        table = new ChainingHashTable<Integer, Integer>(initCapacity, loadFactor)
        then:
        table.loadFactor() == loadFactor
        table.capacity() == capacity
        where:
        initCapacity | initLoadFactor | capacity | loadFactor
        30           | 0.75           | 30       | 0.75d
        10           | 0.85           | 10       | 0.85d
        10           | 0.5            | 10       | 0.5d
        20           | -0.25          | 20       | 0.25d
    }

    def "Test ChainingHashTable default constructor"() {
        given:
        def table = new ChainingHashTable<Integer, Integer>()
        expect:
        table.loadFactor() == 0.75d
        table.capacity() == 10
    }

    def "Test ChainingHashTable capacity constructor"() {
        given:
        def table = new ChainingHashTable<Integer, Integer>(initCapacity)
        expect:
        table.loadFactor() == 0.75d
        table.capacity() == capacity
        where:
        initCapacity | capacity
        30           | 30
        10           | 10
        10           | 10
        20           | 20
    }

    def "Test ChainingHashTable loadFactor constructor"() {
        given:
        def table = new ChainingHashTable<Integer, Integer>(initLoadFactor)
        expect:
        table.capacity() == 10
        table.loadFactor() == loadFactor
        where:
        initLoadFactor | loadFactor
        0.7d           | 0.7d
        0.6d           | 0.6d
        0.3d           | 0.3d
        0.2d           | 0.25d
        -0.1d          | 0.25d
    }

}
