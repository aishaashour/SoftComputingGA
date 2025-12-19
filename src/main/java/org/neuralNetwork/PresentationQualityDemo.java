package org.neuralNetwork;

import org.neuralNetwork.Layer.DenseLayer;
import org.neuralNetwork.Activation.RectifiedLinearUnitFunction;
import org.neuralNetwork.Activation.SigmoidFunction;
import org.neuralNetwork.Initialization.UniformXavierInitializer;
import org.neuralNetwork.Loss.BinaryCross_EntropyLossFunction;

public class PresentationQualityDemo {

    public static void main(String[] args) {

        // Dataset
        CSVDataLoader.Dataset data = CSVDataLoader.load("C:\\Users\\dell\\Desktop\\20221079_20221092_20221055_20220367_CS_S3,4\\src\\main\\java\\org\\neuralNetwork\\dataset\\presentation_quality.csv");

        //train-test Split
        DataSplitter.Split split = DataSplitter.trainTestSplit(data.x, data.y, 0.8, 42);
        double[][] xTrain = split.xTrain;
        double[][] yTrain = split.yTrain;
        double[][] xTest  = split.xTest;
        double[][] yTest  = split.yTest;
        System.out.println("Training samples: " + xTrain.length);
        System.out.println("Testing samples: " + xTest.length);

        //Neural Network
        NeuralNetwork ANN = new NeuralNetwork();
        UniformXavierInitializer initializer = new UniformXavierInitializer();
        double[][] w1 = initializer.initialize(4, 8);
        double[] b1 = new double[8];
        ANN.addLayer(new DenseLayer(4, 8, new RectifiedLinearUnitFunction(), w1, b1));
        double[][] w2 = initializer.initialize(8, 1);
        double[] b2 = new double[1];
        ANN.addLayer(new DenseLayer(8, 1, new SigmoidFunction(), w2, b2));
        ANN.setLossFunction(new BinaryCross_EntropyLossFunction());

        //train Model
        ANN.train(xTrain, yTrain, 800, 2, 0.05);

        //test model
        // =======================
        double[][] testPredictions = ANN.predict(xTest);
        double accuracy = Metrics.accuracy(yTest, testPredictions);
        System.out.println("Model Accuracy = " + (accuracy * 100) + "%");

        //predict
        //bad presentation {0.30, 0.75, 0.20, 0.25}
        //good representation {0.85, 0.15, 0.9, 0.88}
        double[][] testSample = {{0.85, 0.15, 0.9, 0.88}};
        double[][] prediction = ANN.predict(testSample);

        //System.out.println("Probability of GOOD presentation = " + prediction[0][0]);
        System.out.println("Predicted Class for the given example = " + (prediction[0][0] >= 0.5 ? "GOOD" : "BAD"));
    }
}
