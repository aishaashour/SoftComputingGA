package org.neuralNetwork;

import java.util.Random;

public class DataSplitter {

    public static class Split {
        public double[][] xTrain, yTrain;
        public double[][] xTest, yTest;
    }
    public static Split trainTestSplit(
            double[][] x, double[][] y,
            double trainRatio, long seed) {

        int n = x.length;
        int trainSize = (int) (n * trainRatio);

        int[] indices = new int[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Shuffle indices
        Random rand = new Random(seed);
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }
        Split split = new Split();
        split.xTrain = new double[trainSize][x[0].length];
        split.yTrain = new double[trainSize][y[0].length];
        split.xTest = new double[n - trainSize][x[0].length];
        split.yTest = new double[n - trainSize][y[0].length];

        for (int i = 0; i < trainSize; i++) {
            split.xTrain[i] = x[indices[i]];
            split.yTrain[i] = y[indices[i]];
        }

        for (int i = trainSize; i < n; i++) {
            split.xTest[i - trainSize] = x[indices[i]];
            split.yTest[i - trainSize] = y[indices[i]];
        }
        return split;
    }
}
