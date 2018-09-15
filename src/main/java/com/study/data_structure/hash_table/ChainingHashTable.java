package com.study.data_structure.hash_table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.study.algorithm.numerical.NumericalAlgorithms.ceilPowerOfTwo;

public class ChainingHashTable<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 1 << 4;
    private static final int MAX_CAPACITY = 1 << 29;
    private static final float INITIAL_LOAD_FACTOR = 0.75f;

    private volatile List<Bucket> buckets;
    private float loadFactor;
    private int capacity;
    private int size;

    public ChainingHashTable() {
        initBuckets(INITIAL_CAPACITY, INITIAL_LOAD_FACTOR);
    }

    public ChainingHashTable(int initialCapacity) {
        initialCapacity = ceilPowerOfTwo(initialCapacity);
        initBuckets(initialCapacity, INITIAL_LOAD_FACTOR);
    }

    public ChainingHashTable(float loadFactor) {
        initBuckets(INITIAL_CAPACITY, loadFactor);
    }

    public ChainingHashTable(int initialCapacity, float loadFactor) {
        initialCapacity = ceilPowerOfTwo(initialCapacity);
        initBuckets(initialCapacity, loadFactor);
    }

    @Override
    public void put(K key, V value) {
        buckets.get(getIndex(key)).put(key, value);
        if (size > loadFactor * capacity) {
            rehash(capacity >= MAX_CAPACITY ? MAX_CAPACITY : capacity << 1);
        }
    }

    @Override
    public V get(K key) {
        return buckets.get(getIndex(key)).get(key);
    }

    @Override
    public V remove(K key) {
        V removed = buckets.get(getIndex(key)).remove(key);
        if (size < 0.25f * capacity && capacity > INITIAL_CAPACITY) {
            rehash(capacity >> 1);
        }
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public float loadFactor() {
        return loadFactor;
    }

    private void rehash(int capacity) {
        final List<Bucket> oldBuckets = buckets;
        initBuckets(capacity, loadFactor);

        for (Bucket bucket : oldBuckets) {
            for (Entry<K, V> entry : bucket.elements) {
                put(entry.key, entry.value);
            }
        }
    }

    private void initBuckets(int initialCapacity, float loadFactor) {
        initialCapacity = Math.min(Math.max(initialCapacity, INITIAL_CAPACITY), MAX_CAPACITY);
        this.buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            this.buckets.add(new Bucket());
        }
        if (loadFactor < 0.25f) {
            loadFactor = 0.25f;
        }
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        this.size = 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    @Override
    public String toString() {
        return "ChainingHashTable{" +
                "buckets=" + buckets +
                ", loadFactor=" + loadFactor +
                ", capacity=" + capacity +
                ", size=" + size +
                '}';
    }

    class Bucket {
        private List<Entry<K, V>> elements;

        Bucket() {
            elements = new ArrayList<>(0);
        }

        void put(K key, V value) {
            for (Entry<K, V> next : elements) {
                if (next.key.equals(key)) {
                    next.value = value;
                    return;
                }
            }
            elements.add(new Entry<>(key, value));
            size++;
        }

        V get(K key) {
            for (Entry<K, V> next : elements) {
                if (next.key.equals(key)) {
                    return next.value;
                }
            }
            return null;
        }

        V remove(K key) {
            Iterator<Entry<K, V>> it = elements.iterator();
            while (it.hasNext()) {
                Entry<K, V> element = it.next();
                if (element.key.equals(key)) {
                    it.remove();
                    size--;
                    return element.value;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

}
