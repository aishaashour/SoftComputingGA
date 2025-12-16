package org.neuralNetwork.Initialization;

import java.util.Random;

public class UniformXavierInitializer implements Iinitializer {
    private final Random random= new Random();

    @Override
    public double[][] initialize(int inputSize, int outputSize) {
        double[][] weights = new double[inputSize][outputSize];
        double variance = 6.0 / (inputSize + outputSize);
        double stddev = Math.sqrt(variance);
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] = -stddev +  random.nextDouble() * (2 * stddev);
            }
        }
        return weights;
    }

    private double randomGaussian() {
        return Math.random() * 2 - 1; // Placeholder for Gaussian distribution
    }

}
