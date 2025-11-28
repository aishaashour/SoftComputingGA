package org.fuzzyLogic;

import java.util.HashMap;
import java.util.Map;

import org.fuzzyLogic.defuzz.Defuzzifier;
import org.fuzzyLogic.inference.InferenceEngine;
import org.fuzzyLogic.rules.RuleBase;
import org.fuzzyLogic.variable.FuzzySet;
import org.fuzzyLogic.variable.LinguisticVariable;

public class FuzzySystem {
    private final Map<String, LinguisticVariable> variables = new HashMap<>();
    private LinguisticVariable outputVar; //output of whole fuzzy system
    private final RuleBase ruleBase = new RuleBase();//collection of if–then rules
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
    public RuleBase getRuleBase() {  // get rule base so the user can add rules to it.
        return ruleBase;
    }
    public LinguisticVariable getVariable(String name) {
        return variables.get(name);
    }

    public double evaluate(Map<String, Double> inputs) {
        Map<String, Double> fuzzified = new HashMap<>();//  key = variablw name, value = membership degree.

        //fuzzify
        for (var ent : variables.entrySet()) {
            if (ent.getValue() == outputVar)
                continue;
            double x = ent.getValue().Limit(inputs.get(ent.getKey())); //limit to the allowed domain

            // compute membership degree, for each fuzzy set inside this variable.
            for (var fs : ent.getValue().getFuzzySets().entrySet()) {
                double membership = fs.getValue().getMembership(x);
                fuzzified.put(ent.getKey() + ":" + fs.getKey(), membership);//"pitch:low"
            }
        }


        //inference
        //returns an aggregated fuzzy set map for the output variable
        Map<String, Double> aggregated = engine.infer(fuzzified, outputVar, ruleBase.getRules());

        //build output surface (Mamdani only)
        //mapping from crisp output values to their final membership.
        Map<Double, Double> surface = new HashMap<>();

        for (double x = outputVar.Limit(outputVar.Limit(0));
             x <= 100; x += 1) {
            double membership = 0;
            for (var e : aggregated.entrySet()) {
                FuzzySet set = outputVar.getFuzzySets().get(e.getKey());
                membership = Math.max(membership, Math.min(e.getValue(), set.getMembership(x)));
            }
            surface.put(x, membership);
        }


        // defuzzify
        return defuzzifier.defuzzify(surface);
    }
}
