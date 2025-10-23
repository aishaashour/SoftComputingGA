package org.case_study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ga.chromosome.PermutationChromosome;
import org.ga.core.IFeasibilityHandler;

public class EVFeasibilityHandler implements IFeasibilityHandler<Integer, PermutationChromosome> {
    private final int numCars;

    public EVFeasibilityHandler(int numCars) {
        this.numCars = numCars;
    }

    @Override
    public boolean isFeasible(PermutationChromosome chromosome) {
        List<Integer> genes = chromosome.getGenes();
        Set<Integer> unique = new HashSet<>(genes);

        // Ensure all car IDs are unique and within valid range
        return unique.size() == genes.size()
                && Collections.max(genes) < numCars
                && Collections.min(genes) >= 0;
    }

    @Override
    public PermutationChromosome repair(PermutationChromosome chromosome) {
        List<Integer> genes = chromosome.getGenes();
        Set<Integer> seen = new HashSet<>();
        List<Integer> missing = new ArrayList<>();

        // Find missing car IDs
        for (int i = 0; i < numCars; i++) {
            if (!genes.contains(i)) missing.add(i);
        }

        // Replace duplicates with missing IDs
        for (int i = 0; i < genes.size(); i++) {
            int g = genes.get(i);
            if (seen.contains(g)) {
                if (!missing.isEmpty()) {
                    genes.set(i, missing.remove(0));
                }
            } else {
                seen.add(g);
            }
        }

        return chromosome;
    }
}
