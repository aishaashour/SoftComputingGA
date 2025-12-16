package org.neuralNetwork.Activation;

public class LeakyReLUFunction implements  IActivation {
    private final double alpha;

    public LeakyReLUFunction() {
        this.alpha = 0.01; // default alpha value
    }

    public LeakyReLUFunction(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public double[][] activate(double[][] inputs) {
        double[][] outputs = new double[inputs.length][inputs[0].length];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                outputs[i][j] = inputs[i][j] > 0 ? inputs[i][j] : alpha * inputs[i][j];
            }
        }
        return outputs;
    }

    @Override
    public double[][] derivative(double[][] inputs) {
        double[][] derivatives = new double[inputs.length][inputs[0].length];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                derivatives[i][j] = inputs[i][j] > 0 ? 1.0 : alpha;
            }
        }
        return derivatives;
    }

}
