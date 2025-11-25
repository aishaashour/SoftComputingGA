package org.ga.case_study;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.ga.chromosome.PermutationChromosome;
import org.ga.core.GAParameters;
import org.ga.core.GeneticAlgorithm;
import org.ga.core.IFeasibilityHandler;
import org.ga.core.IFitnessFunction;
import org.ga.crossover.ICrossoverStrategy;
import org.ga.crossover.OrderCrossover;
import org.ga.mutation.IMutationStrategy;
import org.ga.mutation.SwapMutation;
import org.ga.replacement.ElitismReplacement;
import org.ga.replacement.IReplacementStrategy;
import org.ga.selection.ISelectionStrategy;
import org.ga.selection.TournamentSelection;

public class EVChargingMain {
    public static void main(String[] args) {
    double[] carDurations = {5.0, 1.5, 7.2, 3.0 , 4.5 , 2.8 , 6.0 , 1.2 , 3.8, 2.0};
    int numCars = carDurations.length;

    // Define GA components
    IFitnessFunction<Integer, PermutationChromosome> fitness = new EVChargingFitness(carDurations);
    IFeasibilityHandler<Integer, PermutationChromosome> feasibility = new EVFeasibilityHandler(numCars);

    GAParameters params = new GAParameters();
    params.setPopulationSize(50);
    params.setGenerations(50); 
    params.setCrossoverRate(0.7);
    params.setMutationRate(0.1);
 

    ISelectionStrategy<Integer, PermutationChromosome> selection = new TournamentSelection<>(3);
    ICrossoverStrategy<Integer, PermutationChromosome> crossover = new OrderCrossover();
    IMutationStrategy<Integer, PermutationChromosome> mutation = new SwapMutation();
    IReplacementStrategy<Integer, PermutationChromosome> replacement = new ElitismReplacement<>(1);

    GeneticAlgorithm<Integer, PermutationChromosome> ga =
            new GeneticAlgorithm<>(params, fitness, selection, crossover, mutation, replacement, feasibility);

    //Initialize random population
    Supplier<PermutationChromosome> initPopulation = () -> {
        List<Integer> genes = new ArrayList<>();
        for (int i = 0; i < numCars; i++) genes.add(i);
        Collections.shuffle(genes);
        return new PermutationChromosome(genes);
    };

    // Run GA
    ga.run(initPopulation);

    //best solution
    PermutationChromosome best = ga.getBestIndividual();
    System.out.println("Best order of cars: " + best.getGenes());
    System.out.println("Best fitness (higher): " + best.getFitness());
}
}
