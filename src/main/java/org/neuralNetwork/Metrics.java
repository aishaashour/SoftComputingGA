package org.neuralNetwork;

public class Metrics {

    public static double accuracy(double[][] yTrue, double[][] yPred) {
        int correct = 0;
        for (int i = 0; i < yTrue.length; i++) {
            int actual = yTrue[i][0] >= 0.5 ? 1 : 0;
            int predicted = yPred[i][0] >= 0.5 ? 1 : 0;
            if (actual == predicted) {
                correct++;
            }
        }
        return (double) correct / yTrue.length;
    }
}
