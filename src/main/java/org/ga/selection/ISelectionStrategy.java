package org.ga.selection;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface ISelectionStrategy<TGene,TChromosome extends Chromosome<TGene>> {
    TChromosome select(List<TChromosome> population);
}
