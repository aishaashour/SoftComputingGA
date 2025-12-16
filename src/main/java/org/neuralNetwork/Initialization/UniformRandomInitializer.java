package org.neuralNetwork.Initialization;

import java.util.Random;

public class UniformRandomInitializer  implements Iinitializer {
    private final double min;
    private final double max;
    private final Random random= new Random();
    public UniformRandomInitializer() {
        this.min = -1.0;
        this.max = 1.0;
    }

    public UniformRandomInitializer(double min, double max) {
        this.min = min;
        this.max = max;
    }
    @Override
    public double[][] initialize(int inputSize, int outputSize) {
        double[][] weights = new double[inputSize][outputSize];
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] = min + random.nextDouble() * (max - min);
            }
        }
        return weights;
    }

    
}