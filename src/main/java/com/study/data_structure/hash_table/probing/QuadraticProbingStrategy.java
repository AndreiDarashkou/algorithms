package com.study.data_structure.hash_table.probing;

final class QuadraticProbingStrategy implements ProbingStrategy {

    static final ProbingStrategy INSTANCE = new QuadraticProbingStrategy();

    private QuadraticProbingStrategy() {
    }

    @Override
    public int nextIndex(Object key, int attempt, int capacity) {
        return Math.abs(key.hashCode()) % capacity + attempt * 2;
    }
}
