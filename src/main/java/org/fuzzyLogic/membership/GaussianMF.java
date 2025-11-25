package org.fuzzyLogic.membership;

public class GaussianMF implements MembershipFunction {
    private final double mean, sigma;

    public GaussianMF(double mean, double sigma) {
        this.mean = mean;
        this.sigma = sigma;
    }

    @Override
    public double getMembership(double x) {
        return Math.exp(-Math.pow(x - mean, 2) / (2 * sigma * sigma));
    }
}
