package org.ga.core;


public class GAParameters {
    public static final int POPULATION_SIZE = 100;
    public static final double MUTATION_RATE = 0.01;
    public static final double CROSSOVER_RATE = 0.7;
    public static final int TOURNAMENT_SIZE = 5;
    public static final int MAX_GENERATIONS = 200;
    public static final int NUM_PARENTS = 2;

    private GAParameters() {
        // Prevent instantiation
    }
}
