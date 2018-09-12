package com.study.data_structure.hash_table.probing;

final class DoubleHashingProbingStrategy implements ProbingStrategy {

    static final ProbingStrategy INSTANCE = new DoubleHashingProbingStrategy();

    private static final int PRIME = 7;

    private DoubleHashingProbingStrategy() {
    }

    @Override
    public int nextIndex(Object key, int attempt, int capacity) {
        return Math.abs(key.hashCode()) % capacity + attempt * hash2(Math.abs(key.hashCode()));
    }

    private int hash2(int hash) {
        return PRIME - (hash % PRIME);
    }

}
