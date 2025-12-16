package org.neuralNetwork.Initialization;

public class ZeroInitializer implements Iinitializer {
    @Override
    public double[][] initialize(int inputSize, int outputSize) {
        return new double[inputSize][outputSize];
    }
    
}
