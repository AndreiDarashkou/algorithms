package com.study.algorithm.network.neural.util;

public class DoubleMatrix {

    int rows;
    int columns;
    int length;
    double[] data;

    DoubleMatrix(int newRows, int newColumns) {
        rows = newRows;
        columns = newColumns;
        length = rows * columns;
        data = new double[newRows * newColumns];
    }

    public DoubleMatrix put(int rowIndex, int columnIndex, double value) {
        data[index(rowIndex, columnIndex)] = value;
        return this;
    }

    public DoubleMatrix put(int i, double v) {
        data[i] = v;
        return this;
    }

    public double get(int rowIndex, int columnIndex) {
        return data[index(rowIndex, columnIndex)];
    }

    public double get(int i) {
        return data[i];
    }

    private int index(int rowIndex, int columnIndex) {
        return rowIndex + rows * columnIndex;
    }

    public DoubleMatrix mmul(DoubleMatrix other) {
        //TODO
        return null;
    }

    public DoubleMatrix mul(DoubleMatrix other) {
        return muli(other, new DoubleMatrix(rows, columns));
    }

    private DoubleMatrix muli(DoubleMatrix other, DoubleMatrix result) {
        if (other.isScalar()) {
            return muli(other.scalar(), result);
        }
        if (isScalar()) {
            return other.muli(scalar(), result);
        }
        assertSameLength(other);
        ensureResultLength(other, result);

        for (int i = 0; i < length; i++) {
            result.put(i, get(i) * other.get(i));
        }
        return result;
    }

    public DoubleMatrix mul(double v) {
        return muli(v, new DoubleMatrix(rows, columns));
    }

    private DoubleMatrix muli(double v, DoubleMatrix result) {
        ensureResultLength(null, result);

        for (int i = 0; i < length; i++) {
            result.put(i, get(i) * v);
        }
        return result;
    }

    public DoubleMatrix sub(DoubleMatrix other) {
        DoubleMatrix result = new DoubleMatrix(rows, columns);
        if (other.isScalar()) {
            return subi(other.scalar(), result);
        }
        if (isScalar()) {
            return other.rsubi(scalar(), result);
        }
        assertSameLength(other);
        ensureResultLength(other, result);

        System.arraycopy(data, 0, result.data, 0, length);
        for (int i = 0; i < length; i++) {
            result.data[i] -= data[i];
        }
        //TODO check
        return result;
    }

    private DoubleMatrix subi(double v, DoubleMatrix result) {
        ensureResultLength(null, result);

        for (int i = 0; i < length; i++) {
            result.put(i, get(i) - v);
        }
        return result;
    }

    private DoubleMatrix rsubi(double a, DoubleMatrix result) {
        ensureResultLength(null, result);

        for (int i = 0; i < length; i++) {
            result.put(i, a - get(i));
        }
        return result;
    }

    public DoubleMatrix addi(DoubleMatrix other) {
        //TODO
        return null;
    }

    private void assertSameLength(DoubleMatrix a) {
        if (!sameLength(a)) {
            throw new IllegalArgumentException("Matrices must have same length (is: " + length + " and " + a.length + ")");
        }
    }

    private void ensureResultLength(DoubleMatrix other, DoubleMatrix result) {
        if (!sameLength(result)) {
            if (result == this || result == other) {
                throw new IllegalArgumentException("Cannot resize result matrix because it is used in-place.");
            }
            result.resize(rows, columns);
        }
    }

    private boolean sameLength(DoubleMatrix a) {
        return length == a.length;
    }

    private void resize(int newRows, int newColumns) {
        rows = newRows;
        columns = newColumns;
        length = newRows * newColumns;
        data = new double[rows * columns];
    }

    private boolean isScalar() {
        return length == 1;
    }

    private double scalar() {
        return get(0);
    }

    public boolean multipliesWith(DoubleMatrix a) {
        return columns == a.rows;
    }

    static DoubleMatrix ones(int rows, int columns) {
        DoubleMatrix result = new DoubleMatrix(rows, columns);
        for (int i = 0; i < rows * columns; i++) {
            result.put(i, 1.0);
        }
        return result;
    }

    public DoubleMatrix transpose() {
        DoubleMatrix result = new DoubleMatrix(columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.put(j, i, get(i, j));
            }
        }
        return result;
    }

}
