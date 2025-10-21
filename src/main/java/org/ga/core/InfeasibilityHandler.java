package org.ga.core;

import org.ga.chromosome.Chromosome;

public interface InfeasibilityHandler {
	boolean isFeasible(Chromosome c);
	Chromosome repair(Chromosome c);
}
