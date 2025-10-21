package org.ga.core;

import java.util.*;
import org.ga.chromosome.*;
import org.ga.crossover.*;
import org.ga.mutation.*;
import org.ga.replacement.*;
import org.ga.selection.*;

public class GeneticAlgorithm {
    // private final GAParameters params;
    private final SelectionStrategy selection;
    private final CrossoverStrategy crossover;
    private final MutationStrategy mutation;
    private final ReplacementStrategy replacement;
    private final FitnessFunction fitnessFunction;
    private final InfeasibilityHandler infeasibilityHandler;
    private final Random random;

    public GeneticAlgorithm(GAParameters params,
                            SelectionStrategy selection,
                            CrossoverStrategy crossover,
                            MutationStrategy mutation,
                            ReplacementStrategy replacement,
                            FitnessFunction fitnessFunction,
                            InfeasibilityHandler infeasibilityHandler) {
        // this.params = params;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.replacement = replacement;
        this.fitnessFunction = fitnessFunction;
        this.infeasibilityHandler = infeasibilityHandler;
        this.random = new Random();
    }

    public Chromosome run(Population initPopulation){
        Population population = initPopulation;

        evaulatePopulation(population);

        Chromosome bestChromosome = bestOf(population);
        for (int generation = 0; generation < GAParameters.MAX_GENERATIONS; generation++) {
            List<Chromosome> offspringList = new ArrayList<>();

            while (offspringList.size() < GAParameters.POPULATION_SIZE) {
                List<Chromosome> parents = selection.select(population, GAParameters.NUM_PARENTS, random);
                Chromosome[] offspringArr = crossover.crossover(parents.get(0), parents.get(1), random);
                List<Chromosome> offspring = new ArrayList<>(Arrays.asList(offspringArr));

                // Chromosome c1 = mutation
                for (Chromosome child : offspring) {
                    mutation.mutate(child, GAParameters.MUTATION_RATE);
                    if(!infeasibilityHandler.isFeasible(child))
                        child = infeasibilityHandler.repair(child);
                    // infeasibilityHandler.handle(child);
                    offspringList.add(child);
                    if (offspringList.size() >= GAParameters.POPULATION_SIZE) {
                        break;
                    }
                }
            }
            Population offspringPop = new Population(offspringList);
            evaulatePopulation(offspringPop);
            // replacement.replace expects (Population current, List<Chromosome> offspring)
            population = replacement.replace(population, offspringList);
            evaulatePopulation(population);

            Chromosome currentBest = bestOf(population);
            if (currentBest.getFitness() > bestChromosome.getFitness()) {
                try {
                    bestChromosome = currentBest.clone();
                } catch (CloneNotSupportedException e) {
                    // fallback: keep current best as-is
                }
            }
        }
        return bestChromosome;
    }

    private void evaulatePopulation(Population population) {
        for (Chromosome chromosome : population.getChromosomes()) {
            double fitness = fitnessFunction.evaluate(chromosome);
            chromosome.setFitness(fitness);
        }
    }
    private Chromosome bestOf(Population population) {
        population.sortByFitness();
        return population.getChromosomes().get(0);
    }
    
}
