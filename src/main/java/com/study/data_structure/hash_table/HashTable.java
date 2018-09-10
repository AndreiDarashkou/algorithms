package com.study.data_structure.hash_table;

public interface HashTable<K, V> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + ',' + value + ']';
        }
    }
}
