package org.ga.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.ga.chromosome.Chromosome;
import org.ga.crossover.ICrossoverStrategy;
import org.ga.mutation.IMutationStrategy;
import org.ga.replacement.IReplacementStrategy;
import org.ga.selection.ISelectionStrategy;


public class GeneticAlgorithm <TGene,TChromosome extends Chromosome<TGene>> {
    private final GAParameters params;
    private final IFitnessFunction<TGene,TChromosome> fitnessFunction;
    private final ISelectionStrategy<TGene,TChromosome> selectionStrategy;
    private final ICrossoverStrategy<TGene,TChromosome> crossoverStrategy;
    private final IMutationStrategy<TGene,TChromosome> mutationStrategy;
    private final IReplacementStrategy<TGene,TChromosome> replacementStrategy;

    private List<TChromosome> population;
    private TChromosome bestIndividual;
    private final Random random = new Random();

    public GeneticAlgorithm(
        GAParameters params,
        IFitnessFunction<TGene,TChromosome> fitnessFunction,
        ISelectionStrategy<TGene,TChromosome> selectionStrategy,
        ICrossoverStrategy<TGene,TChromosome> crossoverStrategy,
        IMutationStrategy<TGene,TChromosome> mutationStrategy,
        IReplacementStrategy<TGene,TChromosome> replacementStrategy
    ) {
        this.params = params;
        this.fitnessFunction = fitnessFunction;
        this.selectionStrategy = selectionStrategy;
        this.crossoverStrategy = crossoverStrategy;
        this.mutationStrategy = mutationStrategy;
        this.replacementStrategy = replacementStrategy;
    }

    public void initializePopulation(Supplier<TChromosome> ChromosomeSupplier) {
        population = new ArrayList<>();
        for (int i = 0; i < params.getPopulationSize(); i++) {
            TChromosome chromosome = ChromosomeSupplier.get();
            population.add(chromosome);
        }
        updateBest();
    }
    private void updateBest() {
        // Track the best individual in the population
        TChromosome genBest = Collections.max(population,Comparator.comparingDouble(Chromosome::getFitness));
        if (bestIndividual == null || genBest.getFitness() > bestIndividual.getFitness()) {
            bestIndividual = (TChromosome)genBest.clone();
        }
    }

    public TChromosome getBestIndividual() {
        return bestIndividual;
    }

    


    public void run(Supplier<TChromosome> initPopulation) {
        // step 1: Initialize population
        // step 2: Evaluate each individual in the population
        // step 3: Select parents using selection strategy
        // step 4: Apply crossover to parents to produce offspring
        // step 5: Apply mutation to offspring
        // step 6: Replace individuals in the population using replacement strategy to form the new population
        // step 7: Track the best solution found so far
        // step 8: Repeat steps 2-6 for a set number of generations or until a termination condition is met


        // step 1
        initializePopulation(initPopulation);
        
        for (int gen = 1; gen <= params.getPopulationSize(); gen++){
            List<TChromosome> newPopulation = new ArrayList<>();

            while(newPopulation.size() < params.getPopulationSize()){
                // step 3
                TChromosome parent1 = selectionStrategy.select(population);
                TChromosome parent2 = selectionStrategy.select(population);

                List<TChromosome> offspring;

                // step 4 crossover
                if(random.nextDouble()< params.getCrossoverRate()){
                    offspring = crossoverStrategy.crossover(parent1, parent2);
                } else {
                    offspring = Arrays.asList((TChromosome)parent1.clone(),(TChromosome)parent2.clone());
                }
                // step 5 mutation
                for(TChromosome child : offspring){
                    if(random.nextDouble() < params.getMutationRate()){
                        mutationStrategy.mutate(child);
                    }
                    child.setFitness(fitnessFunction.evaluate(child));
                    newPopulation.add(child);
                    if(newPopulation.size() >= params.getPopulationSize()){
                        break;
                    }
                }
            }
            // step 6 replacement
            population = replacementStrategy.replace(population, newPopulation);
            // step 7 track best
            updateBest();

        }
    }

    // private void evaulatePopulation(Population population) {
    //     for (Chromosome chromosome : population.getChromosomes()) {
    //         double fitness = fitnessFunction.evaluate(chromosome);
    //         chromosome.setFitness(fitness);
    //     }
    // }
    // private Chromosome bestOf(Population population) {
    //     population.sortByFitness();
    //     return population.getChromosomes().get(0);
    // }
    
}
