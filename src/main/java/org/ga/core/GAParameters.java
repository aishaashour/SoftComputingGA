package org.ga.core;

public class GAParameters {

    // Default values
    private static final int DEFAULT_POPULATION_SIZE = 50;
    private static final double DEFAULT_CROSSOVER_RATE = 0.9;
    private static final double DEFAULT_MUTATION_RATE = 0.05;
    private static final int DEFAULT_GENERATIONS = 100;
    private static final int DEFAULT_TOURNAMENT_SIZE = 3;  // optional
    private static final int DEFAULT_PARENTS_COUNT = 2;    // optional

    // Instance fields
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private int generations;
    private int tournamentSize;
    private int parentsCount;

    // Default constructor (uses default values)
    public GAParameters() {
        this.populationSize = DEFAULT_POPULATION_SIZE;
        this.crossoverRate = DEFAULT_CROSSOVER_RATE;
        this.mutationRate = DEFAULT_MUTATION_RATE;
        this.generations = DEFAULT_GENERATIONS;
        this.tournamentSize = DEFAULT_TOURNAMENT_SIZE;
        this.parentsCount = DEFAULT_PARENTS_COUNT;
    }

    // Custom constructor (user-defined)
    public GAParameters(int populationSize, double crossoverRate, double mutationRate, int generations) {
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.generations = generations;
        this.tournamentSize = DEFAULT_TOURNAMENT_SIZE;
        this.parentsCount = DEFAULT_PARENTS_COUNT;
    }


    public int getPopulationSize() {
        return populationSize;
    }
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }
    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getGenerations() {
        return generations;
    }
    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }
    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public int getParentsCount() {
        return parentsCount;
    }
    public void setParentsCount(int parentsCount) {
        this.parentsCount = parentsCount;
    }
}
