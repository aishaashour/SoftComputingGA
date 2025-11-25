package org.fuzzyLogic;

import java.util.*;
import org.fuzzyLogic.defuzz.Defuzzifier;
import org.fuzzyLogic.inference.InferenceEngine;
import org.fuzzyLogic.rules.RuleBase;
import org.fuzzyLogic.variable.*;

public class FuzzySystem {
    private final Map<String, LinguisticVariable> variables = new HashMap<>();
    private LinguisticVariable outputVar;
    private final RuleBase ruleBase = new RuleBase();
    private InferenceEngine engine;
    private Defuzzifier defuzzifier;

    public void addVariable(LinguisticVariable var, boolean isOutput) {
        variables.put(var.getName(), var);
        if (isOutput)
            outputVar = var;
    }
    public void setEngine(InferenceEngine engine) {
        this.engine = engine;
    }
    public void setDefuzzifier(Defuzzifier defuz) {
        this.defuzzifier = defuz;
    }
    public RuleBase getRuleBase() {
        return ruleBase;
    }

    public double evaluate(Map<String, Double> inputs) {
        Map<String, Double> fuzzified = new HashMap<>();

        // fuzzify
        for (var ent : variables.entrySet()) {
            if (ent.getValue() == outputVar) continue;

            double x = ent.getValue().clamp(inputs.get(ent.getKey()));

            for (var fs : ent.getValue().getFuzzySets().entrySet()) {
                double μ = fs.getValue().getMembership(x);
                fuzzified.put(ent.getKey() + ":" + fs.getKey(), μ);
            }
        }

        // inference
        Map<String, Double> aggregated = engine.infer(fuzzified, outputVar, ruleBase.getRules());

        // build output surface (Mamdani only)
        Map<Double, Double> surface = new HashMap<>();
        for (double x = outputVar.clamp(outputVar.clamp(0));
             x <= 100; x += 1) {  // generic domain
            double membership = 0;
            for (var e : aggregated.entrySet()) {
                FuzzySet set = outputVar.getFuzzySets().get(e.getKey());
                membership = Math.max(membership, Math.min(e.getValue(), set.getMembership(x)));
            }
            surface.put(x, membership);
        }

        return defuzzifier.defuzzify(surface);
    }
}
