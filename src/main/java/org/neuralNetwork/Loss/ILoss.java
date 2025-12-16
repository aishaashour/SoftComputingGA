package org.neuralNetwork.Loss;

public interface ILoss {
    //forward loss computation
    double computeLoss(double[][] predicted, double[][] actual);
    //backword
    double[][] computeGradient(double[][] predicted, double[][] actual);

}
