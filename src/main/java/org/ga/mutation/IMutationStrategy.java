package org.ga.mutation;

import org.ga.chromosome.Chromosome;

public interface IMutationStrategy<T extends Chromosome> {
    void mutate(T chromosome);
}
