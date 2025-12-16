package org.neuralNetwork.Activation;

public class SigmoidFunction implements IActivation {
    @Override
    public double[][] activate(double[][] inputs) {
        double[][] outputs = new double[inputs.length][inputs[0].length];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[0].length; j++) {
                outputs[i][j] = 1.0 / (1.0 + Math.exp(-inputs[i][j]));
            }
        }
        return outputs;
    }

    @Override
    public double[][] derivative(double[][] inputs) {
        double[][] outputs = new double[inputs.length][inputs[0].length];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[0].length; j++) {
                double sigmoid = 1.0 / (1.0 + Math.exp(-inputs[i][j]));
                outputs[i][j] = sigmoid * (1 - sigmoid);
            }
        }
        return outputs;
    }

}
