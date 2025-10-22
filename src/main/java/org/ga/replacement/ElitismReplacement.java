package org.ga.replacement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.ga.chromosome.Chromosome;

public class ElitismReplacement<TGene,TChromosome extends Chromosome<TGene>> implements IReplacementStrategy<TGene,TChromosome>{
    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    @Override
    public List<TChromosome> replace(List<TChromosome> oldPop,List<TChromosome>newPop){
        List<TChromosome>combined = new ArrayList<>(newPop);

        oldPop.sort(Comparator.comparingDouble((Chromosome c) -> c.getFitness()).reversed());
        for (int i = 0; i < eliteCount && i<oldPop.size(); i++) {
            combined.set(i, (TChromosome)oldPop.get(i).clone());
        }
        return combined;
    }


}