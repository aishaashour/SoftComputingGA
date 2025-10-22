package org.ga.crossover;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface ICrossoverStrategy<TChromosome extends Chromosome> {
    List<TChromosome> crossover(TChromosome parent1, TChromosome parent2);

}
