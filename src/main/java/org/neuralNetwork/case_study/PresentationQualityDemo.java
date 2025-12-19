import org.neuralNetwork.NeuralNetwork;
import org.neuralNetwork.Layer.DenseLayer;
import org.neuralNetwork.Activation.RectifiedLinearUnitFunction;
import org.neuralNetwork.Activation.SigmoidFunction;
import org.neuralNetwork.Initialization.UniformXavierInitializer;
import org.neuralNetwork.Loss.BinaryCrossEntropyLoss;

public class PresentationQualityDemo {

    public static void main(String[] args) {

        // ---------------------------
        // Training Data
        // Features:
        // [speechSpeed, pauses, eyeContact, voiceStability]
        // ---------------------------
        double[][] xTrain = {
            {0.8, 0.1, 0.9, 0.85}, // good
            {0.7, 0.2, 0.8, 0.75}, // good
            {0.3, 0.7, 0.2, 0.3},  // bad
            {0.4, 0.6, 0.3, 0.4},  // bad
            {0.9, 0.1, 0.95, 0.9}, // good
            {0.2, 0.8, 0.1, 0.2}   // bad
        };

        // Labels: 1 = Good, 0 = Bad
        double[][] yTrain = {
            {1},
            {1},
            {0},
            {0},
            {1},
            {0}
        };

        // ---------------------------
        // Build Neural Network
        // ---------------------------
        NeuralNetwork nn = new NeuralNetwork();

        nn.addLayer(new DenseLayer(
                4, 8,
                new RectifiedLinearUnitFunction(),
                new UniformXavierInitializer()
        ));

        nn.addLayer(new DenseLayer(
                8, 1,
                new SigmoidFunction(),
                new UniformXavierInitializer()
        ));

        nn.setLossFunction(new BinaryCrossEntropyLoss());

        // ---------------------------
        // Train
        // ---------------------------
        nn.train(
                xTrain,
                yTrain,
                1000,     // epochs
                2,        // batch size
                0.05      // learning rate
        );

        // ---------------------------
        // Test Prediction
        // ---------------------------
        double[][] testSample = {
            {0.85, 0.15, 0.9, 0.88}
        };

        double[][] prediction = nn.predict(testSample);

        System.out.println("\nPrediction Probability (Good Presentation): "
                + prediction[0][0]);
    }
}
