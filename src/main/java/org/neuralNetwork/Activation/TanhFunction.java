package org.neuralNetwork.Activation;

public class TanhFunction implements IActivation {
    @Override
    public double[][] activate(double[][] inputs) {
        int rows = inputs.length;
        int cols = inputs[0].length;
        double[][] outputs = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                outputs[i][j] = Math.tanh(inputs[i][j]);
            }
        }
        return outputs;
    }

    @Override
    public double[][] derivative(double[][] inputs) {
        int rows = inputs.length;
        int cols = inputs[0].length;
        double[][] derivatives = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double tanhValue = Math.tanh(inputs[i][j]);
                derivatives[i][j] = 1 - tanhValue * tanhValue;
            }
        }
        return derivatives;
    }

}
