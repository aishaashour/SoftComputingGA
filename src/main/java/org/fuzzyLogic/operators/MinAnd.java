package org.fuzzyLogic.operators;

public class MinAnd implements AndOperator {
    public double apply(double a, double b) {
        return Math.min(a, b);
    }
}
