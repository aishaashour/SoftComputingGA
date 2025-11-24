package fuzzy;

import java.util.HashMap;
import java.util.Map;

import fuzzy.memberships.MembershipFunctions;

public class FuzzyInput {
    String name;
    double min,max;
    Map<String, MembershipFunctions> fuzzySets;

    public FuzzyInput(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.fuzzySets = new HashMap<>();
    }
    public void addFuzzySet(String setName , MembershipFunctions mf){
        fuzzySets.put(setName, mf);
    }

    public Map<String, Double> fuzzify(double x) {
        Map<String, Double> result = new HashMap<>();
        for (String key : fuzzySets.keySet()) {
            x = Math.max(min, Math.min(max, x)); // ensure x is within bounds
            result.put(key, fuzzySets.get(key).getMembership(x));
        }
        return result;
    }
}
