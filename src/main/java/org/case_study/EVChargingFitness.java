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

        for (int car : order) {
            totalWait += elapsed;          // waiting time for this car
            elapsed += carDurations[car];  // add its charging time
        }

        // Higher fitness for lower total waiting time
        return 1.0 / (1.0 + totalWait);
    }
}
