package com.study.data_structure.hash_table

import com.study.data_structure.hash_table.probing.ProbingType
import spock.lang.Specification
import spock.lang.Unroll

class OpenAddressingHashTableTest extends Specification {

    @Unroll
    def "Test LINEAR open addressing HashTable"() {
        given:
        HashTable<Integer, String> table = new OpenAddressingHashTable<>(ProbingType.LINEAR)
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
        key1 | val1   | key2 | val2    | key3 | val3    | key4 | val4     | key5 | val5      | size | key1Res | key2Res
        1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
    }


    @Unroll
    def "Test PSEUDORANDOM open addressing HashTable"() {
        given:
        HashTable<Integer, String> table = new OpenAddressingHashTable<>(ProbingType.PSEUDORANDOM)
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
        key1 | val1   | key2 | val2    | key3 | val3    | key4 | val4     | key5 | val5      | size | key1Res | key2Res
        1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
    }

    @Unroll
    def "Test QUADRATIC open addressing HashTable"() {
        given:
        HashTable<Integer, String> table = new OpenAddressingHashTable<>(ProbingType.QUADRATIC)
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
        key1 | val1   | key2 | val2    | key3 | val3    | key4 | val4     | key5 | val5      | size | key1Res | key2Res
        1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
    }

    @Unroll
    def "Test DOUBLE_HASHING open addressing HashTable"() {
        given:
        HashTable<Integer, String> table = new OpenAddressingHashTable<>(ProbingType.DOUBLE_HASHING)
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
        key1 | val1   | key2 | val2    | key3 | val3    | key4 | val4     | key5 | val5      | size | key1Res | key2Res
        1    | "One1" | 1    | "One2"  | 2    | "Two"   | 3    | "three"  | 5    | "Five"    | 4    | "One2"  | "One2"
        1    | "One"  | 2    | "Two"   | 1    | "Two"   | 1    | "Three"  | 2    | "Two2"    | 2    | "Three" | "Two2"
        1    | "One"  | 5    | "One"   | 0    | null    | 1    | "One"    | 0    | null      | 3    | "One"   | "One"
        1    | "One"  | 17   | "One17" | 33   | "One33" | 3    | "Three3" | 19   | "Three19" | 5    | "One"   | "One17"
    }

    @Unroll
    def "Test load factor of hash table"() {
        given:
        def table = new OpenAddressingHashTable<>(ProbingType.DOUBLE_HASHING, initCapacity, loadFactor)
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
        def table = new OpenAddressingHashTable<String, String>(ProbingType.LINEAR)
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

}
