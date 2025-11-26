package org.fuzzyLogic.membership;

public class TrapezoidalMF implements MembershipFunction {
    private final double a; 
    private final double b; 
    private final double c; 
    private final double d; 

    public TrapezoidalMF(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double getMembership(double x) {
        if (x <= a || x >= d)
            return 0.0;
        else if (x >= b && x <= c)
            return 1.0;
        else if (x > a && x < b)
            return (x - a) / (b - a);
        else
            return (d - x) / (d - c);
    }
}