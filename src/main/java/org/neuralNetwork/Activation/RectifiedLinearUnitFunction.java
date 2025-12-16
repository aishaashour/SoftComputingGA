package org.neuralNetwork.Activation;

public class RectifiedLinearUnitFunction implements IActivation {
    @Override
    public double[][] activate(double[][] inputs) {
        int rows = inputs.length;
        int cols = inputs[0].length;
        double[][] outputs = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                outputs[i][j] = Math.max(0, inputs[i][j]);
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
                derivatives[i][j] = inputs[i][j] > 0 ? 1 : 0;
            }
        }
        return derivatives;
    }

}
