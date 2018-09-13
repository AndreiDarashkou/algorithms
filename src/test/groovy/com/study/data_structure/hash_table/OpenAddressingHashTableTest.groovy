package com.study.data_structure.hash_table

import spock.lang.Specification
import spock.lang.Unroll

import static com.study.data_structure.hash_table.probing.ProbingType.*

class OpenAddressingHashTableTest extends Specification {

    @Unroll
    def "Test DOUBLE_HASHING open addressing HashTable"() {
        given:
        HashTable<Integer, String> table = new OpenAddressingHashTable<>(probing)
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
        probing        | key1 | val1   | key2 | val2    | key3 | val3    | key4 | val4     | key5 | val5      | size | key1Res | key2Res
        LINEAR         | 1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        LINEAR         | 1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        LINEAR         | 1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        LINEAR         | 1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
        PSEUDORANDOM   | 1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        PSEUDORANDOM   | 1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        PSEUDORANDOM   | 1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        PSEUDORANDOM   | 1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
        QUADRATIC      | 1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        QUADRATIC      | 1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        QUADRATIC      | 1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        QUADRATIC      | 1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
        DOUBLE_HASHING | 1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        DOUBLE_HASHING | 1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        DOUBLE_HASHING | 1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        DOUBLE_HASHING | 1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
    }

    @Unroll
    def "Test load factor of hash table"() {
        given:
        def table = new OpenAddressingHashTable<>(DOUBLE_HASHING, initCapacity, loadFactor)
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
        [1, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41]                | 30           | 0.75       | 32       | 19
        [1, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41, 88, 99, 45]    | 10           | 0.75       | 32       | 21
        [9, 1, 77, 67, 66, 4, 5, 7, 8, 9, 12, 13, 16, 19, 67, 65, 45, 34, 21, 23, 49, 76, 41] | 10           | 0.5        | 64       | 21
    }

    @Unroll
    def "Test remove method"() {
        given:
        def table = new OpenAddressingHashTable<String, String>(probing)
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
        probing        | key1  | val1   | key2   | val2   | key3   | val3  | key4    | val4    | key5   | val5   | size | key1Res | key2Res | nonExistent
        LINEAR         | "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 3    | "One2"  | null    | "six"
        LINEAR         | "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 0    | "Three" | "Two2"  | "seven"
        LINEAR         | "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 1    | "One"   | "One"   | "one"
        PSEUDORANDOM   | "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 3    | "One2"  | null    | "six"
        PSEUDORANDOM   | "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 0    | "Three" | "Two2"  | "seven"
        PSEUDORANDOM   | "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 1    | "One"   | "One"   | "one"
        QUADRATIC      | "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 3    | "One2"  | null    | "six"
        QUADRATIC      | "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 0    | "Three" | "Two2"  | "seven"
        QUADRATIC      | "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 1    | "One"   | "One"   | "one"
        DOUBLE_HASHING | "one" | "One1" | "one"  | "One2" | "two"  | "Two" | "Three" | "three" | "five" | "Five" | 3    | "One2"  | null    | "six"
        DOUBLE_HASHING | "one" | "One"  | "two"  | "Two"  | "one"  | "Two" | "one"   | "Three" | "two"  | "Two2" | 0    | "Three" | "Two2"  | "seven"
        DOUBLE_HASHING | "one" | "One"  | "five" | "One"  | "null" | null  | "one"   | "One"   | "null" | null   | 1    | "One"   | "One"   | "one"
    }

    @Unroll
    def "Test OpenAddressingHashTable allArgs constructor"() {
        given:
        def table
        when:
        table = new OpenAddressingHashTable<Integer, Integer>(LINEAR, initCapacity, loadFactor)
        then:
        table.loadFactor() == loadFactor
        table.capacity() == capacity
        where:
        initCapacity | initLoadFactor | capacity | loadFactor
        30           | 0.75           | 32       | 0.75f
        10           | 0.85           | 16       | 0.85f
        10           | 0.5            | 16       | 0.5f
        20           | -0.25          | 32       | 0.25f
    }

    def "Test OpenAddressingHashTable default constructor"() {
        given:
        def table = new OpenAddressingHashTable<Integer, Integer>()
        expect:
        table.loadFactor() == 0.75f
        table.capacity() == 16
    }

    def "Test OpenAddressingHashTable capacity constructor"() {
        given:
        def table = new OpenAddressingHashTable<Integer, Integer>(LINEAR, initCapacity)
        expect:
        table.loadFactor() == 0.75f
        table.capacity() == capacity
        where:
        initCapacity | capacity
        30           | 32
        10           | 16
        10           | 16
        20           | 32
    }

    def "Test OpenAddressingHashTable loadFactor constructor"() {
        given:
        def table = new OpenAddressingHashTable<Integer, Integer>(LINEAR, initLoadFactor)
        expect:
        table.capacity() == 16
        table.loadFactor() == loadFactor
        where:
        initLoadFactor | loadFactor
        0.7f           | 0.7f
        0.6f           | 0.6f
        0.3f           | 0.3f
        0.2f           | 0.25f
        -0.1f          | 0.25f
    }

    def "test identity with java.util.HashMap"() {
        given:
        def table = new OpenAddressingHashTable<Integer, Integer>(probing)
        def values = new HashMap<Integer, Integer>()
        when:
        for (int i = 0; i < count; i++) {
            int randIndex = (int) (Math.random() * i)
            table.put(randIndex, i * 2)
            values.put(randIndex, table.get(randIndex))
        }
        println(table)
        then:
        table.size() == values.size()
        values.each { k, v -> assert table.get(k) == v }
        where:
        probing        | count
        LINEAR         | 10000
        PSEUDORANDOM   | 10000
        QUADRATIC      | 10000
        DOUBLE_HASHING | 10000
    }

}
