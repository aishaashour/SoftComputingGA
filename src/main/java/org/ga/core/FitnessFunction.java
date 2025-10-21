package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface FitnessFunction {
    double evaluate(Chromosome chromosome);
}
