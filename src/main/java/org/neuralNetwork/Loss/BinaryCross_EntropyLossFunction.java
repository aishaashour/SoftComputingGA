package org.neuralNetwork.Loss;

public class BinaryCross_EntropyLossFunction  implements ILoss {
    
    @Override
    public double computeLoss(double[][] predicted, double[][] actual) {
        int m = actual.length;
        double loss = 0.0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < actual[0].length; j++) {
                loss += - (actual[i][j] * Math.log(predicted[i][j] + 1e-15) +
                        (1 - actual[i][j]) * Math.log(1 - predicted[i][j] + 1e-15));
            }
        }
        return loss / m;
    }

    @Override
    public double[][] computeGradient(double[][] predicted, double[][] actual) {
        int m = actual.length;
        int n = actual[0].length;
        double[][] gradients = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gradients[i][j] = -(actual[i][j] / (predicted[i][j] + 1e-15)) +
                        ((1 - actual[i][j]) / (1 - predicted[i][j] + 1e-15));
                gradients[i][j] /= m;
            }
        }
        return gradients;
    }

}
