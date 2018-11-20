package com.study.algorithm.network.neural;


import com.study.algorithm.network.neural.util.DoubleMatrix;

import static com.study.algorithm.network.neural.util.MathUtil.*;

public class NeuralNetwork implements Network {

    private static final double MIN = 0.01d;

    private final double learningRate;

    private DoubleMatrix weightsHidden;
    private DoubleMatrix outputWeights;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize, double learningRate) {
        this.learningRate = learningRate;

        this.weightsHidden = getRandom(hiddenSize, inputSize, MIN, 0.5d);
        this.outputWeights = getRandom(outputSize, hiddenSize, MIN, 0.5d);
    }

    @Override
    public DoubleMatrix query(DoubleMatrix input) {
        DoubleMatrix hiddenInput = weightsHidden.mmul(input);
        DoubleMatrix hiddenOutput = sigmoid(hiddenInput);
        DoubleMatrix finalInput = outputWeights.mmul(hiddenOutput);

        return sigmoid(finalInput);
    }

    @Override
    public void train(DoubleMatrix input, DoubleMatrix target) {
        DoubleMatrix hiddenInput = weightsHidden.mmul(input);
        DoubleMatrix hiddenOutput = sigmoid(hiddenInput);
        DoubleMatrix finalInput = outputWeights.mmul(hiddenOutput);
        DoubleMatrix finalOutput = sigmoid(finalInput);

        DoubleMatrix outputErrors = target.sub(finalOutput);
        DoubleMatrix hiddenErrors = (outputWeights.transpose()).mmul(outputErrors);

        DoubleMatrix deltaOut = outputErrors.mul(finalOutput).mul((getOnes(finalOutput).sub(finalOutput)));
        DoubleMatrix outputWeightsDelta = deltaOut.mmul(hiddenOutput.transpose()).mul(learningRate);

        DoubleMatrix deltaHidden = hiddenErrors.mul(hiddenOutput).mul((getOnes(hiddenOutput).sub(hiddenOutput)));
        DoubleMatrix deltaWeightsHidden = deltaHidden.mmul(input.transpose()).mul(learningRate);

        weightsHidden.addi(deltaWeightsHidden);
        outputWeights.addi(outputWeightsDelta);
    }

}
