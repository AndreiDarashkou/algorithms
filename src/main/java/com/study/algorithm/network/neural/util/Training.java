package com.study.algorithm.network.neural.util;

public class Training {
    private final DoubleMatrix input;
    private final DoubleMatrix target;

    public Training(DoubleMatrix input, DoubleMatrix target) {
        this.input = input;
        this.target = target;
    }

    public DoubleMatrix getInput() {
        return input;
    }

    public DoubleMatrix getTarget() {
        return target;
    }
}