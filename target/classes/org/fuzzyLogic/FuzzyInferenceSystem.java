package fuzzy;

/**
 * High-level fuzzy inference orchestration.
 */
public class FuzzyInferenceSystem {
    /**
     * Given inputs, run rules and defuzzify.
     */
    public double evaluate(FuzzyInput input) {
        double[] aggregated = FuzzyRules.applyRules(input);
        return Defuzzifier.centroid(aggregated);
    }
}
