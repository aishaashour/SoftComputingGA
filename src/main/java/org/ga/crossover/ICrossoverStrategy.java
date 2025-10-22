package org.ga.crossover;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface ICrossoverStrategy<TGene,TChromosome extends Chromosome<TGene>> {
    List<TChromosome> crossover(TChromosome parent1, TChromosome parent2);

}
