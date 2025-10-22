package org.ga.replacement;

import java.util.ArrayList;
import java.util.List;

import org.ga.chromosome.Chromosome;

public class FitnessBasedReplacement<T extends Chromosome<?>>  implements IReplacementStrategy<T> {
   

    @Override
    public List<T> replace(List<T> currentPopulation, List<T> offspring) {
        int sz = currentPopulation.size();
         List<T> combined = new ArrayList<>(currentPopulation);
        combined.addAll(offspring);

        combined.sort((c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()));

        currentPopulation.clear();
        for (int i = 0; i < sz; i++) {
            currentPopulation.add(combined.get(i));
        }
        return currentPopulation;
    }
    
}
