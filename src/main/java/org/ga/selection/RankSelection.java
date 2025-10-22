package org.ga.selection;

import java.util.List;
import java.util.Random;
import org.ga.chromosome.Chromosome;

public class RankSelection<TGene,TChromosome extends Chromosome<TGene>> implements ISelectionStrategy<TGene,TChromosome> {
    private final Random random = new Random();

    @Override
    public TChromosome select(List<TChromosome> population) {
       
        population.sort((c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()));

        int n = population.size();
        double totalRank = (n * (n + 1)) / 2.0; 

       
        double[] cumulative = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (n-i); 
            cumulative[i] = sum / totalRank;
        }

        
        double r = random.nextDouble();
        for (int i = 0; i < n; i++) {
            if (r <= cumulative[i]) {
                return (TChromosome) population.get(i).clone();
            }
        }

        
        return (TChromosome) population.get(0).clone();
    }
}
