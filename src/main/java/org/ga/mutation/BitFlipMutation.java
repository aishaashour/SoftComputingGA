package org.ga.mutation;

import java.util.List;
import java.util.Random;
import org.ga.chromosome.BinaryChromosome;
import org.ga.chromosome.Chromosome;

public class BitFlipMutation implements IMutationStrategy<Boolean, BinaryChromosome> {
    private final Random random = new Random();

    @Override
    public void mutate(BinaryChromosome chromosome) {
        List<Boolean> genes = chromosome.getGenes();
        int index = random.nextInt(genes.size());
        // Flip bit (true -> false, false -> true)
        genes.set(index, !genes.get(index));
    }
}
