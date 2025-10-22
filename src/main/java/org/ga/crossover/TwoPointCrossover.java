package org.ga.crossover;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

import org.ga.chromosome.IntegerChromosome;

public class TwoPointCrossover implements ICrossoverStrategy<Integer, IntegerChromosome> {
    private final Random random = new Random();
    private final Map<String, List<IntegerChromosome>> cache = new HashMap<>();


    @Override
    public List<IntegerChromosome> crossover(IntegerChromosome parent1, IntegerChromosome parent2) {
        int sz = parent1.getGenes().size();
        String key = generateKey(parent1, parent2);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int point1 = 1+ random.nextInt(sz-1);
        int point2 = 1+ random.nextInt(sz-1);

        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

      
        List<Integer> genes1 = new ArrayList<>(parent1.getGenes());
        List<Integer> genes2 = new ArrayList<>(parent2.getGenes());

       
        for (int i = point1; i < point2; i++) {
            int temp = genes1.get(i);
            genes1.set(i, genes2.get(i));
            genes2.set(i, temp);
        }

       
        IntegerChromosome offspring1 = new IntegerChromosome(genes1);
        IntegerChromosome offspring2 = new IntegerChromosome(genes2);
        cache.put(key, List.of(offspring1, offspring2));

        return List.of(offspring1, offspring2);
    }
      private String generateKey(IntegerChromosome p1, IntegerChromosome p2) {
        return p1.getGenes().hashCode() + "-" + p2.getGenes().hashCode();
    }
}
