package org.fuzzyLogic.inference;

import org.fuzzyLogic.rules.FuzzyRule;
import org.fuzzyLogic.variable.*;

import java.util.Map;

public interface InferenceEngine {
    Map<String, Double> infer(
            Map<String, Double> fuzzifiedInputs,
            LinguisticVariable outputVar,
            Iterable<FuzzyRule> rules
    );
}
