package org.neuralNetwork.Initialization;

public class NormalXavierInitializer implements Iinitializer {
    @Override
    public double[][] initialize(int inputSize, int outputSize) {
        double[][] weights = new double[inputSize][outputSize];
        double variance = 2.0 / (inputSize + outputSize);
        double stddev = Math.sqrt(variance);

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] = stddev * randomGaussian();
            }
        }
        return weights;
    }

    private double randomGaussian() {
        return Math.random() * 2 - 1; // Simple uniform distribution between -1 and 1
    }
    
}
