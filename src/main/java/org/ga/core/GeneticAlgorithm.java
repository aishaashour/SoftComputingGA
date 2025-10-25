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
    private final IFeasibilityHandler<TGene, TChromosome> feasibilityHandler;

    private List<TChromosome> population;
    private TChromosome bestIndividual;
    private final Random random = new Random();

    public GeneticAlgorithm(
            GAParameters params,
            IFitnessFunction<TGene,TChromosome> fitnessFunction,
            ISelectionStrategy<TGene,TChromosome> selectionStrategy,
            ICrossoverStrategy<TGene,TChromosome> crossoverStrategy,
            IMutationStrategy<TGene,TChromosome> mutationStrategy,
            IReplacementStrategy<TGene,TChromosome> replacementStrategy,
            IFeasibilityHandler<TGene, TChromosome> feasibilityHandler
    ) {
        this.params = params;
        this.fitnessFunction = fitnessFunction;
        this.selectionStrategy = selectionStrategy;
        this.crossoverStrategy = crossoverStrategy;
        this.mutationStrategy = mutationStrategy;
        this.replacementStrategy = replacementStrategy;
        this.feasibilityHandler = feasibilityHandler;
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
    //1.intialize population
    initializePopulation(initPopulation);
    //2.evaluate fitness
    for (TChromosome chromosome : population) {
        chromosome.setFitness(fitnessFunction.evaluate(chromosome));
    }
    updateBest();

    double prevBestFitness = bestIndividual.getFitness();

    //3.evolution loop
    for (int gen = 1; gen <= params.getGenerations(); gen++) {
        List<TChromosome> newPopulation = new ArrayList<>();

        //selection
        while (newPopulation.size() < params.getPopulationSize()) {
            TChromosome parent1 = selectionStrategy.select(population);
            TChromosome parent2 = selectionStrategy.select(population);

            List<TChromosome> offspring;

            //crossover
            if (random.nextDouble() < params.getCrossoverRate()) {
                offspring = crossoverStrategy.crossover(parent1, parent2);
            } else {
                offspring = Arrays.asList(
                        (TChromosome) parent1.clone(),(TChromosome) parent2.clone()
                );
            }

            //mutation and feasibility check
            for (TChromosome child : offspring) {
                if (random.nextDouble() < params.getMutationRate()) {
                    mutationStrategy.mutate(child);
                }
                if (feasibilityHandler != null && !feasibilityHandler.isFeasible(child)) {
                    child = feasibilityHandler.repair(child);
                }
                child.setFitness(fitnessFunction.evaluate(child));
                //new generation
                newPopulation.add(child);
                if (newPopulation.size() >= params.getPopulationSize()) {
                    break;
                }
            }
        }
        //replacement

        population = replacementStrategy.replace(population, newPopulation);
        updateBest();

        // Print only when fitness improves or for the first generation
        double bestFitness = bestIndividual.getFitness();
        if (gen == 1 || bestFitness > prevBestFitness) {
            System.out.println("Generation " + gen + " | Best Fitness: " + bestFitness);
            prevBestFitness = bestFitness;
        }
    }
}
    
}
