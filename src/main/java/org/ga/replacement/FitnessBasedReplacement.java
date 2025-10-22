package org.ga.replacement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ga.chromosome.Chromosome;


public class FitnessBasedReplacement<TGene,TChromosome extends Chromosome<TGene>>  implements IReplacementStrategy<TGene,TChromosome> {
     private final Map<String, List<TChromosome>> cache = new HashMap<>();


    @Override
    public List<TChromosome> replace(List<TChromosome> currentPopulation, List<TChromosome> offspring) {
        int sz = currentPopulation.size();
          String key = generateKey(currentPopulation, offspring);

        
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        List<TChromosome> combined = new ArrayList<>(currentPopulation);
        combined.addAll(offspring);

        combined.sort((c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()));

        currentPopulation.clear();
        for (int i = 0; i < sz; i++) {
            currentPopulation.add(combined.get(i));
        }
        cache.put(key, currentPopulation);
        return currentPopulation;
    }


    private String generateKey(List<TChromosome> currentPopulation, List<TChromosome> offspring) {
        int hashCurrent = currentPopulation.stream().mapToInt(Chromosome::hashCode).sum();
        int hashOffspring = offspring.stream().mapToInt(Chromosome::hashCode).sum();
        return hashCurrent + "-" + hashOffspring;
    }

    
}
