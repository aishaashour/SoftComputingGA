package org.case_study;

import org.ga.core.*;
import org.ga.chromosome.*;
import org.ga.selection.*;
import org.ga.crossover.*;
import org.ga.mutation.*;
import org.ga.replacement.*;

import java.util.*;
import java.util.function.Supplier;

public class EVChargingMain {
    public static void main(String[] args) {
        // Example: 6 cars, one charging slot
        double[] carDurations = {2.5, 3.0, 1.2, 4.0, 2.8, 1.5};
        int numCars = carDurations.length;

        // 1️⃣ Create fitness and feasibility handler
        IFitnessFunction<Integer, PermutationChromosome> fitness = new EVChargingFitness(carDurations);
        IFeasibilityHandler<Integer, PermutationChromosome> feasibility = new EVFeasibilityHandler(numCars);

        // 2️⃣ Configure GA parameters
        GAParameters params = new GAParameters();
        params.setPopulationSize(50);
        params.setGenerations(100);
        params.setCrossoverRate(0.7);
        params.setMutationRate(0.1);

        // 3️⃣ Define GA strategies
        ISelectionStrategy<Integer, PermutationChromosome> selection = new TournamentSelection<>(3);
        ICrossoverStrategy<Integer, PermutationChromosome> crossover = new OrderCrossover();
        IMutationStrategy<Integer, PermutationChromosome> mutation = new SwapMutation();
        IReplacementStrategy<Integer, PermutationChromosome> replacement = new ElitismReplacement<>(2); // e.g. top 2 elites

        // 4️⃣ Instantiate the GA
        GeneticAlgorithm<Integer, PermutationChromosome> ga =
                new GeneticAlgorithm<>(params, fitness, selection, crossover, mutation, replacement, feasibility);

        // ✅ 5️⃣ Define how to initialize chromosomes
        Supplier<PermutationChromosome> initPopulation = () -> {
            List<Integer> genes = new ArrayList<>();
            for (int i = 0; i < numCars; i++) genes.add(i);
            Collections.shuffle(genes);
            return new PermutationChromosome(genes);
        };

        // ✅ 6️⃣ Run the GA with the initialization supplier
        ga.run(initPopulation);

        // 7️⃣ Display best result
        PermutationChromosome best = ga.getBestIndividual();
        System.out.println("Best order of cars: " + best.getGenes());
        System.out.println("Best fitness (lower = better): " + best.getFitness());
    }
}
