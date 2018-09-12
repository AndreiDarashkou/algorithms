package com.study.data_structure.hash_table.probing;

final class LinearProbingStrategy implements ProbingStrategy {

    static final ProbingStrategy INSTANCE = new LinearProbingStrategy();

    private LinearProbingStrategy() {
    }

    @Override
    public int nextIndex(Object key, int attempt, int capacity) {
        return Math.abs(key.hashCode()) % capacity + attempt;
    }
}
