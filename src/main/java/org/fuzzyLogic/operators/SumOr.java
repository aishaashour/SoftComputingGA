package org.fuzzyLogic.operators;

public class SumOr implements OrOperator {
    public double apply(double a, double b) {
        return Math.min(1.0, a + b);
    }
}
