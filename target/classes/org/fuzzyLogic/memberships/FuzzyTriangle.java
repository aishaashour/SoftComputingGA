package fuzzy.memberships;

public class FuzzyTriangle  extends MembershipFunctions {
    private final double a; 
    private final double b; 
    private final double c; 
    
    public FuzzyTriangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getMembership(double x) {
        if (x <= a || x >= c) return 0.0;
        else if (x == b) return 1.0;
        else if (x > a && x < b) return (x - a) / (b - a);
        else return (c - x) / (c - b);
    }
}