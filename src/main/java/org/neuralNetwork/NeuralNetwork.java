package org.neuralNetwork;

import java.util.ArrayList;
import java.util.List;

import org.neuralNetwork.Layer.ILayer;
import org.neuralNetwork.Loss.ILoss;


public class NeuralNetwork {

    private List<ILayer> layers;
    private ILoss lossFunction;
    private List<Double> lossHistory = new ArrayList<>(); 


    public NeuralNetwork() {
        this.layers = new ArrayList<>();
    }

    public void addLayer(ILayer layer) {
        this.layers.add(layer);
    }

    public void setLossFunction(ILoss lossFunction) {
        this.lossFunction = lossFunction;
    }
    public List<Double> getLossHistory() {   
        return lossHistory;
    }

    //Forward Propagation
    public double[][] predict(double[][] input) {
        double[][] output = input;
        for (ILayer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }

    //Training Loop
    public void train(double[][] xTrain, double[][] yTrain,
                      int epochs, int batchSize, double learningRate) {

        int sampleSize = xTrain.length;

        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalEpochLoss = 0.0;
            int batches = 0;

            for (int start = 0; start < sampleSize; start += batchSize) {
                int end = Math.min(start + batchSize, sampleSize);

                double[][] xBatch = slice(xTrain, start, end);
                double[][] yBatch = slice(yTrain, start, end);

                //Forward
                double[][] predictions = predict(xBatch);

                //Loss
                totalEpochLoss += lossFunction.computeLoss(predictions, yBatch);
                batches++;

                //Backward
                double[][] gradient = lossFunction.computeGradient(predictions, yBatch);
                for (int i = layers.size() - 1; i >= 0; i--) {
                    gradient = layers.get(i).backward(gradient, learningRate);
                }
            }

            double avgLoss = totalEpochLoss / batches;
            lossHistory.add(avgLoss);   
            System.out.println("Epoch " + (epoch + 1) + " | Loss: " + avgLoss);
        }
    }

    private double[][] slice(double[][] data, int start, int end) {
        int length = end - start;
        double[][] batch = new double[length][data[0].length];
        for (int i = 0; i < length; i++) {
            batch[i] = data[start + i];
        }
        return batch;
    }
}
