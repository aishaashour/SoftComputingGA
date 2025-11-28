package org.fuzzyLogic.inference;

import java.util.*;
import org.fuzzyLogic.operators.AndOperator;
import org.fuzzyLogic.rules.FuzzyRule;
import org.fuzzyLogic.variable.LinguisticVariable;

public class MamdaniEngine implements InferenceEngine {

    private final AndOperator andOp;
    public MamdaniEngine(AndOperator andOp) {
        this.andOp = andOp;
    }

    @Override
    public Map<String, Double> infer(
            Map<String, Double> fuzzified,
            LinguisticVariable outVar,
            Iterable<FuzzyRule> rules)
    {
        Map<String, Double> aggregated = new HashMap<>();

        for (FuzzyRule rule : rules) {
            if (!rule.isEnabled())
                continue;

            double fire = 1.0;

            for (FuzzyRule.Condition ant : rule.getConditions().values()) {
                double v = fuzzified.get(ant.var() + ":" + ant.set());
                fire = andOp.apply(fire, v);
            }

            fire *= rule.getWeight();
            aggregated.merge(rule.getOutputSet(), fire, Math::max);
        }
        return aggregated;
    }
}
