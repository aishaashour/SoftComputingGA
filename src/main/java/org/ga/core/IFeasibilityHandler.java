package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface IFeasibilityHandler<T> {
	boolean isFeasible(Chromosome<T> c);
	Chromosome<T> repair(Chromosome<T> c);
}
