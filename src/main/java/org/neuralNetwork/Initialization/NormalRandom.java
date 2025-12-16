package org.neuralNetwork.Initialization;

import java.util.Random;

public class NormalRandom  implements Iinitializer {
    private final double mean;
    private final double stddev;
    private final Random rand= new java.util.Random();

    public NormalRandom() {
        mean = 0.0;
        stddev = 1.0;
    }
    public NormalRandom(double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
    }
    @Override
    public double[][] initialize(int inputSize, int outputSize) {
        double[][] weights = new double[inputSize][outputSize];
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] = mean + stddev * rand.nextGaussian();
            }
        }
        return weights;
    }

}
