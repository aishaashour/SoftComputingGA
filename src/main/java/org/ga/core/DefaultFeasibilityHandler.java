package org.ga.core;

import org.ga.chromosome.Chromosome;

public class DefaultFeasibilityHandler<TGene, TChromosome extends Chromosome<TGene>>
        implements IFeasibilityHandler<TGene, TChromosome> {

    @Override
    public boolean isFeasible(TChromosome c) {
        // By default, assume all chromosomes are valid
        return true;
    }

    @Override
    public TChromosome repair(TChromosome c) {
        // Return it as it is (no repair needed)
        return c;
    }
}
