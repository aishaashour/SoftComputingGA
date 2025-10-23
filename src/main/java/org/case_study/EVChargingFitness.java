package org.case_study;

import java.util.List;

import org.ga.chromosome.PermutationChromosome;
import org.ga.core.IFitnessFunction;

public class EVChargingFitness implements IFitnessFunction<Integer, PermutationChromosome> {
    private final double[] carDurations;

    public EVChargingFitness(double[] carDurations) {
        this.carDurations = carDurations;
    }

    @Override
    public double evaluate(PermutationChromosome chromosome) {
        List<Integer> order = chromosome.getGenes();
        double totalWait = 0.0;
        double elapsed = 0.0;

        // ✅ Quiet safety check — avoids crashes and console spam
        for (int car : order) {
            if (car < 0 || car >= carDurations.length) {
                // Invalid gene → return very poor fitness
                return 0.0;
            }
        }

        // ✅ Calculate total waiting time
        for (int car : order) {
            totalWait += elapsed;
            elapsed += carDurations[car];
        }

        // ✅ Fitness: higher = better (lower total wait)
        return 1.0 / (1.0 + totalWait);
    }
}
