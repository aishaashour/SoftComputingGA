package org.fuzzyLogic.UseCase;

import java.util.Map;

import org.fuzzyLogic.FuzzySystem;
import org.fuzzyLogic.defuzz.CentroidDefuzzifier;
import org.fuzzyLogic.inference.MamdaniEngine;
import org.fuzzyLogic.membership.TriangularMF;
import org.fuzzyLogic.operators.MinAnd;
import org.fuzzyLogic.rules.FuzzyRule;
import org.fuzzyLogic.rules.RuleBase;
import org.fuzzyLogic.variable.FuzzySet;
import org.fuzzyLogic.variable.LinguisticVariable;

public class MoodDetection {
    public static FuzzySystem buildFuzzySystem() {
        FuzzySystem fs = new FuzzySystem();

        // ================= INPUT VARIABLES ==================
        // Pitch (50-400 Hz)
        LinguisticVariable pitch = new LinguisticVariable("Pitch", 50, 400);
        pitch.addFuzzySet(new FuzzySet("Low", new TriangularMF(50, 50, 255)));
        pitch.addFuzzySet(new FuzzySet("Medium", new TriangularMF(125, 255, 325)));
        pitch.addFuzzySet(new FuzzySet("High", new TriangularMF(225, 400, 400)));
        fs.addVariable(pitch, false);

        // Speech Rate (50-200 words)
        LinguisticVariable sr = new LinguisticVariable("SpeechRate", 50, 200);
        sr.addFuzzySet(new FuzzySet("Slow", new TriangularMF(50, 50, 125)));
        sr.addFuzzySet(new FuzzySet("Normal", new TriangularMF(75, 125, 175)));
        sr.addFuzzySet(new FuzzySet("Fast", new TriangularMF(125, 200, 200)));
        fs.addVariable(sr, false);

        // Volume (0-100)
        LinguisticVariable volume = new LinguisticVariable("Volume", 0, 100);
        volume.addFuzzySet(new FuzzySet("Low", new TriangularMF(0, 0, 50)));
        volume.addFuzzySet(new FuzzySet("Medium", new TriangularMF(25, 50, 75)));
        volume.addFuzzySet(new FuzzySet("High", new TriangularMF(50, 100, 100)));
        fs.addVariable(volume, false);

        // ================= OUTPUT VARIABLE ==================
        LinguisticVariable emotionalState = new LinguisticVariable("EmotionalState", 0, 10);
        emotionalState.addFuzzySet(new FuzzySet("Sad", new TriangularMF(0, 0, 3)));
        emotionalState.addFuzzySet(new FuzzySet("Calm", new TriangularMF(2, 4, 6)));
        emotionalState.addFuzzySet(new FuzzySet("Happy", new TriangularMF(5, 7, 9)));
        emotionalState.addFuzzySet(new FuzzySet("Angry", new TriangularMF(8, 10, 10)));
        fs.addVariable(emotionalState, true);

        // ================= RULES ==================
        RuleBase rb = fs.getRuleBase();

        rb.addRule(new FuzzyRule(
                Map.of(
                        "Pitch", new FuzzyRule.Condition("Pitch", "Low"),
                        "SpeechRate", new FuzzyRule.Condition("SpeechRate", "Slow"),
                        "Volume", new FuzzyRule.Condition("Volume", "Low")),
                "Sad"));
        rb.addRule(new FuzzyRule(
                Map.of(
                        "Pitch", new FuzzyRule.Condition("Pitch", "High"),
                        "SpeechRate", new FuzzyRule.Condition("SpeechRate", "Fast"),
                        "Volume", new FuzzyRule.Condition("Volume", "High")),
                "Angry"));
        rb.addRule(new FuzzyRule(
                Map.of(
                        "Pitch", new FuzzyRule.Condition("Pitch", "Medium"),
                        "SpeechRate", new FuzzyRule.Condition("SpeechRate", "Normal"),
                        "Volume", new FuzzyRule.Condition("Volume", "Medium")),
                "Calm"));
        rb.addRule(new FuzzyRule(
                Map.of(
                        "Pitch", new FuzzyRule.Condition("Pitch", "High"),
                        "SpeechRate", new FuzzyRule.Condition("SpeechRate", "Normal"),
                        "Volume", new FuzzyRule.Condition("Volume", "Medium")),
                "Happy"));
        // ================= ENGINE & DEFUZZIFIER ==================
        fs.setEngine(new MamdaniEngine(new MinAnd()));
        fs.setDefuzzifier(new CentroidDefuzzifier());

        return fs;
    }

    public static void main(String[] args) {

        FuzzySystem fs = buildFuzzySystem();

       
        Map<String, Double>[] inputs = new Map[] {
                Map.of("Pitch", 80.0, "SpeechRate", 60.0, "Volume", 20.0), // Sad
                Map.of("Pitch", 250.0, "SpeechRate", 130.0, "Volume", 50.0), // Calm
                Map.of("Pitch", 300.0, "SpeechRate", 130.0, "Volume", 60.0), // Happy
                Map.of("Pitch", 350.0, "SpeechRate", 180.0, "Volume", 90.0) // Angry
        };

        LinguisticVariable emotionalState = new LinguisticVariable("EmotionalState", 0, 10);
        emotionalState.addFuzzySet(new FuzzySet("Sad", new TriangularMF(0, 0, 3)));
        emotionalState.addFuzzySet(new FuzzySet("Calm", new TriangularMF(2, 4, 6)));
        emotionalState.addFuzzySet(new FuzzySet("Happy", new TriangularMF(5, 7, 9)));
        emotionalState.addFuzzySet(new FuzzySet("Angry", new TriangularMF(8, 10, 10)));

        for (Map<String, Double> input : inputs) {
            double crisp = fs.evaluate(input);
            String bestLabel = null;
            double bestMu = -1;
            for (var e : emotionalState.getFuzzySets().entrySet()) {
                double mu = e.getValue().getMembership(crisp);
                if (mu > bestMu) {
                    bestMu = mu;
                    bestLabel = e.getKey();
                }
            }
            System.out.println("Input: " + input);
            System.out.println("Crisp: " + crisp + ", Detected Mood: " + bestLabel + " (membership=" + bestMu + ")");
            System.out.println("-------------------------------");
        }
    }
}
