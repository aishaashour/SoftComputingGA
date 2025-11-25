package org.fuzzyLogic.rules;

import java.util.Map;

public class FuzzyRule { // single IF–THEN rule

    public record Condition(String var, String set) {}

    private final Map<String, Condition> Conditions;
    private final String outputSet;
    private boolean enabled = true;
    private double weight = 1.0;

    public FuzzyRule(Map<String, Condition> antecedents, String outputSet) {
        this.Conditions = antecedents;
        this.outputSet = outputSet;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public Map<String, Condition> getConditions() {
        return Conditions;
    }
    public String getOutputSet() {
        return outputSet;
    }
}
