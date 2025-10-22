package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface IFeasibilityHandler<TGene, TChromosome extends Chromosome<TGene>> {
	boolean isFeasible(TChromosome c);
	TChromosome repair(TChromosome c);
}
