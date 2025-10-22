package org.ga.mutation;

import org.ga.chromosome.Chromosome;

public interface IMutationStrategy<TGene,TChromosome extends Chromosome<TGene>> {
    void mutate(TChromosome chromosome);
}
