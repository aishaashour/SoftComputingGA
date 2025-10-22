package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface IFitnessFunction <TGene,TChromosome extends Chromosome<TGene>> {
    double evaluate(TChromosome chromosome);
}
