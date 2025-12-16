package org.neuralNetwork.Layer;

public interface ILayer {

    public double[][] forward(double[][] inputs);
    public double[][] backward(double[][] gradients, double learningRate);

}
