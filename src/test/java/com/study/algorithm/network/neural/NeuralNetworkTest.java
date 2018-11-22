package com.study.algorithm.network.neural;

import com.study.algorithm.network.neural.util.DoubleMatrix;
import com.study.algorithm.network.neural.util.Training;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test simple neural network")
class NeuralNetworkTest {

    @Test
    @DisplayName("Train and check network (xor method)")
    void trainAndCheckNetwork() {
        Network neuralNetwork = new NeuralNetwork(2, 4, 1, 0.5);
        List<Training> trainingSet = xorTrainingSet();

        int epoch = 1;
        while (true) {
            for (Training training : trainingSet) {
                neuralNetwork.train(training.getInput(), training.getTarget());
            }
            double setError = calculateEpochError(neuralNetwork, trainingSet);
            if (setError < 0.05) {
                break;
            }
            System.out.println("Epoch: " + epoch + " error: " + (((int) (setError * 1000)) / 10d) + "%");
            epoch++;
        }

        for (Training training : trainingSet) {
            assertEquals(Math.round(training.getTarget().get(0)), Math.round(neuralNetwork.query(training.getInput()).get(0)));
        }
    }

    private static double calculateEpochError(Network neuralNetwork, List<Training> trainingSet) {
        double setError = 0.0d;
        for (Training value : trainingSet) {
            setError += Math.pow(value.getTarget().get(0) - neuralNetwork.query(value.getInput()).get(0), 2);
        }
        return setError / trainingSet.size();
    }

    private static List<Training> xorTrainingSet() {
        DoubleMatrix ideal0 = new DoubleMatrix(new double[]{0.01});
        DoubleMatrix ideal1 = new DoubleMatrix(new double[]{0.99});

        DoubleMatrix input1 = new DoubleMatrix(2, 1, 0.99, 0.01);
        DoubleMatrix input2 = new DoubleMatrix(2, 1, 0.01, 0.01);
        DoubleMatrix input3 = new DoubleMatrix(2, 1, 0.01, 0.99);
        DoubleMatrix input4 = new DoubleMatrix(2, 1, 0.99, 0.99);

        List<Training> trainingSet = new ArrayList<>();

        trainingSet.add(new Training(input1, ideal1));
        trainingSet.add(new Training(input2, ideal0));
        trainingSet.add(new Training(input3, ideal1));
        trainingSet.add(new Training(input4, ideal0));

        return trainingSet;
    }

}