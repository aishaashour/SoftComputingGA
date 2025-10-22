package org.ga.test;

import org.ga.chromosome.IntegerChromosome;
import org.ga.selection.RankSelection;

import java.util.ArrayList;
import java.util.List;

public class TestRankSelection {

    public static void main(String[] args) {
        
        int populationSize = 5;
        int chromosomeSize = 6;

        List<IntegerChromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            IntegerChromosome chrom = new IntegerChromosome(chromosomeSize);

            
            chrom.setFitness(i + 1); 
            population.add(chrom);
        }

        
        RankSelection<Integer, IntegerChromosome> rankSelection = new RankSelection<>();

       
        System.out.println("Population fitnesses:");
        for (IntegerChromosome chrom : population) {
            System.out.println(chrom.getFitness());
        }

        System.out.println("\nSelected chromosomes:");
        for (int i = 0; i < 10; i++) {
            IntegerChromosome selected = rankSelection.select(population);
            System.out.println(selected.getGenes() + " Fitness: " + selected.getFitness());
        }
    }
}
