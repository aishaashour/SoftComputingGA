package org.ga.replacement;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface IReplacementStrategy<T extends Chromosome> {
    List<T> replace(List<T> currentPopulation, List<T> offspring);
   
}
