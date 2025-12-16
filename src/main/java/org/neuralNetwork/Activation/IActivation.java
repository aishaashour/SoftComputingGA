package org.neuralNetwork.Activation;

public interface IActivation {

    //forward activation function
    double [] [] activate(double[][] inputs);
    //derivative of activation function
    double [] []derivative(double[][] inputs);

}
