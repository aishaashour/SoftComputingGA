package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface IFitnessFunction <TChromosome extends Chromosome> {
    double evaluate(TChromosome chromosome);
}
