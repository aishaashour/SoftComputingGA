package org.ga.crossover;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface CrossoverStrategy<T extends Chromosome> {
    List<T> crossover(T parent1, T parent2);

}
