package org.neuralNetwork.Loss;

public class MSEFunction implements ILoss {
    @Override
    public double computeLoss(double[][] predicted, double[][] actual) {
        double sum = 0.0;
        int n = predicted.length;
        int m = predicted[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double diff = predicted[i][j] - actual[i][j];
                sum += diff * diff;
            }
        }
        return sum / (n * m);
    }

    @Override
    public double[][] computeGradient(double[][] predicted, double[][] actual) {
        int n = predicted.length;
        int m = predicted[0].length;
        double[][] gradient = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gradient[i][j] = 2 * (predicted[i][j] - actual[i][j]) / (n * m);
            }
        }
        return gradient;
    }   

}
