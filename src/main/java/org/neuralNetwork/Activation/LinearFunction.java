package org.neuralNetwork.Activation;

public class LinearFunction implements IActivation {

    @Override
    public double[][] activate(double[][] inputs) {
        return inputs;
    }

    @Override
    public double[][] derivative(double[][] inputs) {
        double[][] derivatives = new double[inputs.length][inputs[0].length];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[0].length; j++) {
                derivatives[i][j] = 1.0;
            }
        }
        return derivatives;
    }

}
