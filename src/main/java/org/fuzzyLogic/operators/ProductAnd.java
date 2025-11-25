package org.fuzzyLogic.operators;

public class ProductAnd implements AndOperator {
    public double apply(double a, double b) {
        return a * b;
    }
}
