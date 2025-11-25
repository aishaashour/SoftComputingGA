package org.fuzzyLogic.inference;

import java.util.*;
import org.fuzzyLogic.operators.AndOperator;
import org.fuzzyLogic.rules.FuzzyRule;

public class SugenoEngine implements InferenceEngine {

    private final AndOperator andOp;
    private final Map<String, Double> outputs;

    public SugenoEngine(AndOperator andOp, Map<String, Double> outputValues) {
        this.andOp = andOp;
        this.outputs = outputValues;
    }

    @Override
    public Map<String, Double> infer(
            Map<String, Double> fuzzified,
            org.fuzzyLogic.variable.LinguisticVariable outVar,
            Iterable<FuzzyRule> rules)
    {
        Map<String, Double> result = new HashMap<>();

        double numerator = 0, denominator = 0;

        for (FuzzyRule rule : rules) {
            double fire = 1;

            for (FuzzyRule.Condition ant : rule.getConditions().values())
                fire = andOp.apply(fire, fuzzified.get(ant.var() + ":" + ant.set()));

            fire *= rule.getWeight();

            numerator += fire * outputs.get(rule.getOutputSet());
            denominator += fire;
        }

        result.put("SugenoOutput", numerator / denominator);
        return result;
    }
}
