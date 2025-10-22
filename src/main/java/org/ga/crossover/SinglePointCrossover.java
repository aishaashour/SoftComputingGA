package org.ga.crossover;

import java.util.List;
import java.util.Random;

import org.ga.chromosome.IntegerChromosome;

public class SinglePointCrossover implements ICrossoverStrategy<Integer,IntegerChromosome> {
    private final Random random = new Random();

    @Override
    public List<IntegerChromosome> crossover(IntegerChromosome parent1, IntegerChromosome parent2) {
        int sz = parent1.getGenes().size();
        int crossoverPoint = 1 + random.nextInt(sz - 1); 

        IntegerChromosome offspring1 = new IntegerChromosome(sz);
        IntegerChromosome offspring2 = new IntegerChromosome(sz);

        for (int i = 0; i < sz; i++) {
            if (i < crossoverPoint) {
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
