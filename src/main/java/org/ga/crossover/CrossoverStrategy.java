package org.ga.crossover;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface CrossoverStrategy {
    Chromosome[] crossover(Chromosome parent1, Chromosome parent2, Random rnd);
}
