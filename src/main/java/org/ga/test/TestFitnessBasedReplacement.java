package org.ga.test;

import org.ga.chromosome.IntegerChromosome;
import org.ga.replacement.FitnessBasedReplacement;

import java.util.ArrayList;
import java.util.List;

public class TestFitnessBasedReplacement  {

    public static void main(String[] args) {
        int populationSize = 5;
        int chromosomeSize = 6;

       
        List<IntegerChromosome> currentPopulation = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            IntegerChromosome chrom = new IntegerChromosome(chromosomeSize);
            chrom.setFitness(i + 1); 
            currentPopulation.add(chrom);
        }

       
        List<IntegerChromosome> offspring = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            IntegerChromosome child = new IntegerChromosome(chromosomeSize);
            child.setFitness(i + 6); 
            offspring.add(child);
        }

        
        FitnessBasedReplacement<Integer, IntegerChromosome> replacer = new FitnessBasedReplacement<>();
        List<IntegerChromosome> newPopulation = replacer.replace(currentPopulation, offspring);

        
        System.out.println("New population after replacement:");
        for (IntegerChromosome chrom : newPopulation) {
            System.out.println("Genes: " + chrom.getGenes() + " Fitness: " + chrom.getFitness());
        }
    }
}
