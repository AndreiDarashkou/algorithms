package com.study.algorithm.network.neural.util;


public final class MathUtil {

    private final static double E = 0.7;
    private final static double A = 0.5;

    public static DoubleMatrix sigmoid(DoubleMatrix x) {
        DoubleMatrix res = new DoubleMatrix(x.rows, x.columns);
        for (int i = 0; i < x.length; i++) {
            res.put(i, sigmoid(x.get(i)));
        }
        return res;
    }

    private static double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }

    public static DoubleMatrix calcHiddenDelta(DoubleMatrix result, DoubleMatrix hiddenDelta) {
        DoubleMatrix delta = new DoubleMatrix(result.rows, hiddenDelta.columns);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < hiddenDelta.columns; j++) {
                delta.put(i, j, calcHiddenDelta(result.get(i, j), hiddenDelta.get(i, j)));
            }
        }
        return delta;
    }

    public static DoubleMatrix getOnes(DoubleMatrix sample) {
        return DoubleMatrix.ones(sample.rows, sample.columns);
    }

    public static DoubleMatrix getRandom(int rows, int columns, double minVal, double maxVal) {
        DoubleMatrix matrix = new DoubleMatrix(rows, columns);

        for (int i = 0; i < rows * columns; i++) {
            matrix.data[i] = minVal + Math.random() * (maxVal - minVal);
        }
        return matrix;
    }

    private static double calculateDeltaWeight(double gradient, double prevWeight) {
        return E * gradient + A * prevWeight;
    }

    private static double calcHiddenDelta(double output, double sumSinDelta) {
        return sumSinDelta * derivativeFunction(output);
    }

    private static double calcOutputDelta(double ideal, double output) {
        return (ideal - output) * derivativeFunction(output);
    }

    private static double derivativeFunction(double output) {
        return (1.0 - output) * output;
    }

}
