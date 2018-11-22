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

    public DoubleMatrix(double[] newData) {
        this(newData.length, 1, newData);
    }

    public DoubleMatrix(int newRows, int newColumns, double... newData) {
        rows = newRows;
        columns = newColumns;
        length = rows * columns;
        data = newData;
    }

    public DoubleMatrix put(int row, int column, double value) {
        data[index(row, column)] = value;
        return this;
    }

    public DoubleMatrix put(int i, double v) {
        data[i] = v;
        return this;
    }

    public double get(int row, int column) {
        return data[index(row, column)];
    }

    public double get(int i) {
        return data[i];
    }

    private int index(int row, int column) {
        return row * columns + column;
    }

    public DoubleMatrix mmul(DoubleMatrix other) {
        return mmuli(other, new DoubleMatrix(this.rows, other.columns));
    }

    private DoubleMatrix mmuli(DoubleMatrix other, DoubleMatrix result) {
        if (other.isScalar()) {
            return muli(other.scalar(), result);
        } else if (isScalar()) {
            return other.muli(scalar(), result);
        } else {
            if (result.rows != rows || result.columns != other.columns) {
                result.resize(this.rows, other.columns);
            }
            if (other.columns == 1) {
                gemv(this, other, result);
            } else {
                gemm(this, other, result);
            }
            return result;
        }
    }

    private static void gemm(DoubleMatrix a, DoubleMatrix b, DoubleMatrix result) {
        int aRows = a.rows;
        int aColumns = a.columns;
        int bColumns = b.columns;

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    result.put(i, j, result.get(i, j) + a.get(i, k) * b.get(k, j));
                }
            }
        }
    }

    private static void gemv(DoubleMatrix a, DoubleMatrix x, DoubleMatrix y) {
        int j;
        for (j = 0; j < y.length; ++j) {
            y.data[j] = 0.0D;
        }
        for (j = 0; j < a.columns; ++j) {
            double xj = x.get(j);
            if (xj != 0.0D) {
                for (int i = 0; i < a.rows; ++i) {
                    y.data[i] += 1.0D * a.get(i, j) * xj;
                }
            }
        }
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
            result.data[i] -= other.data[i];
        }
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

    public void addi(DoubleMatrix other) {
        if (other.isScalar()) {
            addi(other.scalar(), this);
        } else if (isScalar()) {
            other.addi(scalar(), this);
        } else {
            assertSameLength(other);
            ensureResultLength(other, this);
            for (int c = 0; c < length; ++c) {
                data[c] += other.data[c];
            }
        }
    }

    private void addi(double v, DoubleMatrix result) {
        ensureResultLength(null, result);

        for (int i = 0; i < this.length; ++i) {
            result.put(i, this.get(i) + v);
        }
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