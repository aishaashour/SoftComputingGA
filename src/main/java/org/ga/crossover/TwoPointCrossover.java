package org.ga.crossover;

import java.util.List;
import java.util.Random;

import org.ga.chromosome.IntegerChromosome;

public class TwoPointCrossover implements ICrossoverStrategy<IntegerChromosome> {
    private final Random random = new Random();
    private IntegerChromosome offspring1;
    private IntegerChromosome offspring2;
    
    @Override
    public List<IntegerChromosome> crossover(IntegerChromosome parent1, IntegerChromosome parent2) {
        int sz=parent1.getGenes().size();
        int point1 = random.nextInt(sz);
        int point2 = random.nextInt(sz);
        
        
        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }
        
        offspring1 = new IntegerChromosome(sz);
        offspring2 = new IntegerChromosome(sz);
        
        for (int i = 0; i < sz; i++) {
            if (i < point1 || i >= point2) {
                offspring1.getGenes().set(i, parent1.getGenes().get(i));
                offspring2.getGenes().set(i, parent2.getGenes().get(i));
            } else {
                offspring1.getGenes().set(i, parent2.getGenes().get(i));
                offspring2.getGenes().set(i, parent1.getGenes().get(i));
            }
        }
        
        return List.of(offspring1, offspring2);
    }
    
}
