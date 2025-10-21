package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface FitnessFunction <T extends Chromosome> {
    double evaluate(T chromosome);
}
