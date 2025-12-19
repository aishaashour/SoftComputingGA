package org.neuralNetwork;

import org.neuralNetwork.Layer.DenseLayer;
import org.neuralNetwork.Activation.RectifiedLinearUnitFunction;
import org.neuralNetwork.Activation.SigmoidFunction;
import org.neuralNetwork.Initialization.UniformXavierInitializer;
import org.neuralNetwork.Loss.BinaryCross_EntropyLossFunction;

public class PresentationQualityDemo {

    public static void main(String[] args) {
        //dataset
        CSVDataLoader.Dataset data = CSVDataLoader.load("C:\\Users\\dell\\Desktop\\SoftComputingGA\\src\\main\\java\\org\\neuralNetwork\\dataset\\presentation_quality.csv");

        double[][] xTrain = data.x;
        double[][] yTrain = data.y;

        //build Neural Network (intialization)
        NeuralNetwork ANN = new NeuralNetwork();
        UniformXavierInitializer initializer = new UniformXavierInitializer();

        double[][] w1 = initializer.initialize(4, 8);
        double[] b1 = new double[8];
        ANN.addLayer(new DenseLayer(4, 8, new RectifiedLinearUnitFunction(), w1, b1));
        double[][] w2 = initializer.initialize(8, 1);
        double[] b2 = new double[1];
        ANN.addLayer(new DenseLayer(8, 1, new SigmoidFunction(), w2, b2));
        ANN.setLossFunction(new BinaryCross_EntropyLossFunction());

        // Train
        ANN.train(xTrain, yTrain, 1000, 2, 0.05);

        // testing
        //bad presentation {0.30, 0.75, 0.20, 0.25}
        //average presentation  {0.60, 0.40, 0.55, 0.50}
        //good representation {0.85, 0.15, 0.9, 0.88}
        double[][] testSample = {{0.85, 0.15, 0.9, 0.88}};
        double[][] prediction = ANN.predict(testSample);
        System.out.println("Probability of GOOD presentation = " + prediction[0][0]);
    }
}
