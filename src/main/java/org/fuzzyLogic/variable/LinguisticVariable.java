package org.fuzzyLogic.variable;
import java.util.HashMap;
import java.util.Map;

public class LinguisticVariable {
    private final String name;
    private final double min, max;
    private final Map<String, FuzzySet> sets = new HashMap<>();

    public LinguisticVariable(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public void addFuzzySet(FuzzySet set) {
        sets.put(set.getName(), set);
    }

    public Map<String, FuzzySet> getFuzzySets() {
        return sets;
    }

    public String getName() {
        return name;
    }

    public double clamp(double x) {
        return Math.max(min, Math.min(max, x));
    }
}
