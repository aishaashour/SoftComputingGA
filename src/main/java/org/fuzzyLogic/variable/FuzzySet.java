package org.fuzzyLogic.variable;

import org.fuzzyLogic.membership.MembershipFunction;

public class FuzzySet {
    private final String name;
    private final MembershipFunction mf;

    public FuzzySet(String name, MembershipFunction mf) {
        this.name = name;
        this.mf = mf;
    }

    public double getMembership(double x) {
        return mf.getMembership(x);
    }

    public String getName() {
        return name;
    }
}
