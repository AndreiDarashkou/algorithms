package com.study.data_structure.hash_table.probing;

public final class ProbingFactory {

    private ProbingFactory() {
    }

    public static ProbingStrategy getStrategy(ProbingType probingType) {
        switch (probingType) {
            case LINEAR:
                return LinearProbingStrategy.INSTANCE;
            case QUADRATIC:
                return QuadraticProbingStrategy.INSTANCE;
            case PSEUDORANDOM:
                return PseudorandomProbingStrategy.INSTANCE;
            case DOUBLE_HASHING:
                return DoubleHashingProbingStrategy.INSTANCE;
            default:
                return LinearProbingStrategy.INSTANCE;
        }
    }

}
