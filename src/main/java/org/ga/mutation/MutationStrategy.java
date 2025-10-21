package org.ga.mutation;

import org.ga.chromosome.Chromosome;

public interface MutationStrategy<T extends Chromosome> {
    void mutate(T chromosome);
}
