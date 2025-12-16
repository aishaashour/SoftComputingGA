package org.neuralNetwork.Layer;

import org.neuralNetwork.Activation.IActivation;

public class DenseLayer implements ILayer {
    private int inputSize;
    private int outputSize;
    private double[][] weights;
    private double[] biases;
    private double[][] input; // stored for backward
    private double[][] z; // pre-activation
    private IActivation activation;

    public DenseLayer(int inputSize, int outputSize, IActivation activation, double[][] weights, double[] biases) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activation = activation;
        this.weights = weights;
        this.biases = biases;
    }

    @Override
    public double[][] forward(double[][] inputs) {
        this.input = inputs;
        int batchSize = inputs.length;
        z = new double[batchSize][outputSize];
        double[][] output = new double[batchSize][outputSize];

        // Compute z = inputs * weights + biases
        for (int i = 0; i < batchSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                for (int k = 0; k < inputSize; k++) {
                    z[i][j] += inputs[i][k] * weights[k][j];
                }
                z[i][j] += biases[j]; // Add bias once per neuron
            }
        }

        // Apply activation
        output = activation.activate(z);
        return output;
    }

    @Override
    public double[][] backward(double[][] gradients, double learningRate) {
        int batchSize = gradients.length;

        // Compute delta = gradients * activation'(z)
        double[][] zDerivatives = activation.derivative(z);
        double[][] delta = new double[batchSize][outputSize];
        for (int i = 0; i < batchSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                delta[i][j] = gradients[i][j] * zDerivatives[i][j];
            }
        }

        // Gradients w.r.t weights and biases
        double[][] weightGradients = new double[inputSize][outputSize];
        double[] biasGradients = new double[outputSize];

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                for (int k = 0; k < batchSize; k++) {
                    weightGradients[i][j] += input[k][i] * delta[k][j];
                }
            }
        }

        for (int j = 0; j < outputSize; j++) {
            for (int k = 0; k < batchSize; k++) {
                biasGradients[j] += delta[k][j];
            }
        }

        // Update weights and biases
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] -= learningRate * weightGradients[i][j];
            }
        }

        for (int j = 0; j < outputSize; j++) {
            biases[j] -= learningRate * biasGradients[j];
        }

        // Gradients w.r.t input to propagate to previous layer: inputGradients = delta * weights^T
        double[][] inputGradients = new double[batchSize][inputSize];
        for (int i = 0; i < batchSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                for (int k = 0; k < outputSize; k++) {
                    inputGradients[i][j] += delta[i][k] * weights[j][k]; // Corrected transpose
                }
            }
        }

        return inputGradients;
    }
}
