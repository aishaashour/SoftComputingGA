package org.ga.mutation;

import java.util.List;
import java.util.Random;
import org.ga.chromosome.DoubleChromosome;

public class UniformMutation implements IMutationStrategy<Double, DoubleChromosome> {
    private final Random random = new Random();
    private final double lowerBound;
    private final double upperBound;
    private final double mutationProbability;

    // Constructor allows specifying range and probability
    public UniformMutation(double lowerBound, double upperBound, double mutationProbability) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.mutationProbability = mutationProbability;
    }

    @Override
    public void mutate(DoubleChromosome chromosome) {
        List<Double> genes = chromosome.getGenes();
        int size = genes.size();

        for (int i = 0; i < size; i++) {
            if (random.nextDouble() < mutationProbability) {
                // Replace the gene with a new uniform random value in [lowerBound, upperBound]
                double newGene = lowerBound + (upperBound - lowerBound) * random.nextDouble();
                genes.set(i, newGene);
            }
        }
    }
}
