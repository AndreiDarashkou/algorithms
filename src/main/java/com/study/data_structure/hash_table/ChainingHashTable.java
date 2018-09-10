package com.study.data_structure.hash_table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChainingHashTable<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 10;
    private static final double INITIAL_LOAD_FACTOR = 0.75d;

    private List<Bucket<K, V>> buckets;
    private double loadFactor;
    private int capacity;
    private int size;

    public ChainingHashTable() {
        initBuckets(INITIAL_CAPACITY, INITIAL_LOAD_FACTOR);
    }

    public ChainingHashTable(int initialCapacity) {
        initBuckets(initialCapacity, INITIAL_LOAD_FACTOR);
    }

    public ChainingHashTable(double loadFactor) {
        initBuckets(INITIAL_CAPACITY, loadFactor);
    }

    public ChainingHashTable(int initialCapacity, double loadFactor) {
        initBuckets(initialCapacity, loadFactor);
    }

    @Override
    public void put(K key, V value) {
        buckets.get(getIndex(key)).put(key, value);
        if (size > loadFactor * capacity) {
            rehash();
        }
    }

    @Override
    public V get(K key) {
        return buckets.get(getIndex(key)).get(key);
    }

    @Override
    public V remove(K key) {
        return buckets.get(getIndex(key)).remove(key);
    }

    public int size() {
        return size;
    }


    public int capacity() {
        return capacity;
    }

    public double loadFactor() {
        return loadFactor;
    }

    private void rehash() {
        synchronized (buckets) {
            List<Bucket<K, V>> oldBuckets = buckets;
            capacity *= 2;
            initBuckets(capacity, loadFactor);

            for (Bucket<K, V> bucket : oldBuckets) {
                for (Entry<K, V> entry : bucket.elements) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    private void initBuckets(int initialCapacity, double loadFactor) {
        this.buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            this.buckets.add(new Bucket<K, V>());
        }
        if (loadFactor < 0.25d) {
            loadFactor = 0.25d;
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

    class Bucket<K, V> {
        private List<Entry<K, V>> elements;

        Bucket() {
            elements = new ArrayList<>(0);
        }

        public void put(K key, V value) {
            for (Entry<K, V> next : elements) {
                if (next.key.equals(key)) {
                    next.value = value;
                    return;
                }
            }
            elements.add(new Entry<>(key, value));
            size++;
        }

        public V get(K key) {
            for (Entry<K, V> next : elements) {
                if (next.key.equals(key)) {
                    return next.value;
                }
            }
            return null;
        }

        public V remove(K key) {
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
