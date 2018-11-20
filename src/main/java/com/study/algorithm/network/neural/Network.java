package com.study.algorithm.network.neural;


import com.study.algorithm.network.neural.util.DoubleMatrix;

public interface Network {

    DoubleMatrix query(DoubleMatrix input);

    void train(DoubleMatrix input, DoubleMatrix target);

}
