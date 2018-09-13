package com.study.data_structure.hash_table;

import com.study.data_structure.hash_table.probing.ProbingFactory;
import com.study.data_structure.hash_table.probing.ProbingStrategy;
import com.study.data_structure.hash_table.probing.ProbingType;

import java.util.Arrays;

import static com.study.algorithm.NumericalAlgorithms.ceilPowerOfTwo;

public class OpenAddressingHashTable<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 1 << 4;
    private static final int MAX_CAPACITY = 1 << 29;
    private static final float INITIAL_LOAD_FACTOR = 0.75f;
    private static final Entry EMPTY = new Entry<>(Integer.MIN_VALUE, Integer.MIN_VALUE);

    private final ProbingStrategy probingStrategy;
    private Entry<K, V>[] elements;
    private int capacity;
    private int size;
    private float loadFactor;

    public OpenAddressingHashTable() {
        this.probingStrategy = ProbingFactory.getStrategy(ProbingType.LINEAR);
        init(INITIAL_CAPACITY, INITIAL_LOAD_FACTOR);
    }

    public OpenAddressingHashTable(ProbingType probingType) {
        this.probingStrategy = ProbingFactory.getStrategy(probingType);
        init(INITIAL_CAPACITY, INITIAL_LOAD_FACTOR);
    }

    public OpenAddressingHashTable(ProbingType probingType,int initialCapacity) {
        this.probingStrategy = ProbingFactory.getStrategy(probingType);
        init(ceilPowerOfTwo(initialCapacity), INITIAL_LOAD_FACTOR);
    }

    public OpenAddressingHashTable(ProbingType probingType, float loadFactor) {
        this.probingStrategy = ProbingFactory.getStrategy(probingType);
        init(INITIAL_CAPACITY, loadFactor);
    }

    public OpenAddressingHashTable(ProbingType probingType, int initialCapacity, float loadFactor) {
        this.probingStrategy = ProbingFactory.getStrategy(probingType);
        init(ceilPowerOfTwo(initialCapacity), loadFactor);
    }

    @Override
    public void put(K key, V value) {
        int attempt = 0;
        int index = probingStrategy.nextIndex(key, attempt, capacity);
        while (index < this.capacity) {
            Entry<K, V> cur = elements[index];
            if (cur == null || cur == EMPTY) {
                size++;
                break;
            }
            if (cur.key.equals(key)) {
                break;
            }
            index = probingStrategy.nextIndex(key, ++attempt, capacity);
        }
        if (index >= this.capacity) {
            rehash();
            put(key, value);
        } else {
            elements[index] = new Entry<>(key, value);
        }
        if (size > capacity * loadFactor) {
            rehash();
        }
    }

    @Override
    public V get(K key) {
        int attempt = 0;
        int index = probingStrategy.nextIndex(key, attempt, capacity);
        while (index < this.capacity) {
            Entry<K, V> cur = elements[index];
            if (cur == null) {
                return null;
            }
            if (cur.key.equals(key)) {
                return cur.value;
            }
            index = probingStrategy.nextIndex(key, ++attempt, capacity);
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int attempt = 0;
        int index = probingStrategy.nextIndex(key, attempt, capacity);
        while (index < this.capacity) {
            Entry<K, V> cur = elements[index];
            if (cur == null) {
                return null;
            }
            if (cur.key.equals(key)) {
                elements[index] = EMPTY;
                size--;
                return cur.value;
            }
            index = probingStrategy.nextIndex(key, ++attempt, capacity);
        }
        return null;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public float loadFactor() {
        return loadFactor;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        if (capacity == MAX_CAPACITY) {
            throw new IllegalStateException("Cannot add more elements");
        }
        Entry<K, V>[] oldElements = elements;
        capacity = capacity << 1;
        init(capacity, loadFactor);
        for (Entry<K,V> entry: oldElements) {
            if (entry != null && entry != EMPTY) {
                put(entry.key, entry.value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void init(int capacity, float loadFactor) {
        this.size = 0;
        this.loadFactor = loadFactor >= 0.25f ? loadFactor : 0.25f;
        this.capacity = capacity;
        this.elements = new Entry[this.capacity];
    }

    @Override
    public String toString() {
        return "OpenAddressingHashTable{" +
                "elements=" + Arrays.toString(elements) +
                ", capacity=" + capacity +
                ", size=" + size +
                ", loadFactor=" + loadFactor +
                '}';
    }
}
