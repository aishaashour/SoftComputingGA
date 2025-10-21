package org.ga.mutation;

import org.ga.chromosome.Chromosome;

public interface MutationStrategy {
    void mutate(Chromosome chromosome, double mutationRate);
}
