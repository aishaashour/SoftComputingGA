package org.fuzzyLogic.rules;

import java.util.*;

public class RuleBase {
    private final List<FuzzyRule> rules = new ArrayList<>();

    public void addRule(FuzzyRule rule) {
        rules.add(rule);
    }
    public void removeRule(FuzzyRule rule) {
        rules.remove(rule);
    }
    public List<FuzzyRule> getRules() {
        return rules;
    }
}
