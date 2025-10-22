package org.ga.core;


public class GAParameters {
    private final int populationSize;
    private final double crossoverRate;
    private final  double mutationRate;
    private final int generations;
    public GAParameters(int populationSize, double crossoverRate, double mutationRate, int generations) {
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.generations = generations;
    }

    public int getPopulationSize() { return populationSize; }
    public double getCrossoverRate() { return crossoverRate; }
    public double getMutationRate() { return mutationRate; }
    public int getGenerations() { return generations; }

}
