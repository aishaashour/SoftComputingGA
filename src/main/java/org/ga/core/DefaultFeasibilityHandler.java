package org.ga.core;

import org.ga.chromosome.Chromosome;

public class DefaultFeasibilityHandler<TGene, TChromosome extends Chromosome<TGene>>
        implements IFeasibilityHandler<TGene, TChromosome> {

    @Override
    public boolean isFeasible(TChromosome c) {
        return true;
    }

    @Override
    public TChromosome repair(TChromosome c) {
        return c;
    }
}
