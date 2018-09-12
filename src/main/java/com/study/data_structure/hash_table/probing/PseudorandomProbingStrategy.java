package com.study.data_structure.hash_table.probing;

final class PseudorandomProbingStrategy implements ProbingStrategy {

    static final ProbingStrategy INSTANCE = new PseudorandomProbingStrategy();

    private final int random;

    private PseudorandomProbingStrategy() {
        random = 2 + (int) (Math.random() * 4d);
    }

    @Override
    public int nextIndex(Object key, int attempt, int capacity) {
        return Math.abs(key.hashCode()) % capacity + attempt * random;
    }
}
