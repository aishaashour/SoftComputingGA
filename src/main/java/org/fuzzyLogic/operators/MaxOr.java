package org.fuzzyLogic.operators;

public class MaxOr implements OrOperator {
    public double apply(double a, double b) {
        return Math.max(a, b);
    }
}
