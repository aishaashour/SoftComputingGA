package org.ga.replacement;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface IReplacementStrategy<TGene,TChromosome extends Chromosome<TGene>> {
    List<TChromosome> replace(List<TChromosome> currentPopulation, List<TChromosome> offspring);
   
}
