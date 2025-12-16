package org.neuralNetwork.Loss;

public class CategoricalCross_EntropyLoss  implements ILoss {
    //
    @Override
    public double computeLoss(double[][] predicted, double[][] actual) {
        int samples = predicted.length;
        double loss = 0.0;

        for (int i = 0; i < samples; i++) {
            for (int j = 0; j < predicted[i].length; j++) {
                if (actual[i][j] == 1) {
                    loss -= Math.log(predicted[i][j] + 1e-15); // Adding a small value to avoid log(0)
                }
            }
        }
        return loss / samples;
    }

    @Override
    public double[][] computeGradient(double[][] predicted, double[][] actual) {
        int samples = predicted.length;
        int classes = predicted[0].length;
        double[][] gradients = new double[samples][classes];

        for (int i = 0; i < samples; i++) {
            for (int j = 0; j < classes; j++) {
                gradients[i][j] = -actual[i][j] / (predicted[i][j] + 1e-15); // Adding a small value to avoid division by zero
            }
        }

        // Average the gradients over the batch
        for (int i = 0; i < samples; i++) {
            for (int j = 0; j < classes; j++) {
                gradients[i][j] /= samples;
            }
        }

        return gradients;
    }

}
