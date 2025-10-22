

package org.ga.test;

import org.ga.chromosome.IntegerChromosome;
import org.ga.crossover.SinglePointCrossover;
import java.util.List;

public class TestSinglePointCrossover {

    public static void main(String[] args) {
        IntegerChromosome parent1 = new IntegerChromosome(new int[]{1, 2, 3, 4, 5, 6});
        IntegerChromosome parent2 = new IntegerChromosome(new int[]{9, 8, 7, 6, 5, 4});

        
        SinglePointCrossover crossover = new SinglePointCrossover();

       
        List<IntegerChromosome> offspring = crossover.crossover(parent1, parent2);

        
        System.out.println("Parent 1:   " + parent1.getGenes());
        System.out.println("Parent 2:   " + parent2.getGenes());
        System.out.println("Offspring 1:" + offspring.get(0).getGenes());
        System.out.println("Offspring 2:" + offspring.get(1).getGenes());
    }
}
