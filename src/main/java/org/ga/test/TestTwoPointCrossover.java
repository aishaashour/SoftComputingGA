package org.ga.test;

import java.util.List;

import org.ga.chromosome.IntegerChromosome;

import org.ga.crossover.TwoPointCrossover;

public class TestTwoPointCrossover {
    public static void main(String[] args) {
        IntegerChromosome parent1 = new IntegerChromosome(8);
        IntegerChromosome parent2 = new IntegerChromosome(8);

        
        TwoPointCrossover crossover = new TwoPointCrossover();

       
        List<IntegerChromosome> offspring = crossover.crossover(parent1, parent2);

        
        System.out.println("Parent 1:   " + parent1.getGenes());
        System.out.println("Parent 2:   " + parent2.getGenes());
        System.out.println("Offspring 1:" + offspring.get(0).getGenes());
        System.out.println("Offspring 2:" + offspring.get(1).getGenes());
   
 }

}