package org.ga.replacement;

import java.util.List;
import org.ga.chromosome.Chromosome;

public class GenerationalReplacement<TGene, TChromosome extends Chromosome<TGene>>
        implements IReplacementStrategy<TGene, TChromosome> {

    @Override
    public List<TChromosome> replace(List<TChromosome> oldPop, List<TChromosome> newPop) {
        // Replace entire old population with new population
        return newPop;
    }
}
