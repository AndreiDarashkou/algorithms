package com.study.data_structure.hash_table.probing;

@FunctionalInterface
public interface ProbingStrategy {

    int nextIndex(Object key, int attempt, int capacity);

}
