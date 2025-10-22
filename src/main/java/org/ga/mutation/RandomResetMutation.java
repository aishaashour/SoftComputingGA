package org.ga.mutation;

import java.util.List;
import java.util.Random;
import org.ga.chromosome.IntegerChromosome;

public class RandomResetMutation implements IMutationStrategy<Integer, IntegerChromosome> {
    private final Random random = new Random();

    @Override
    public void mutate(IntegerChromosome chromosome) {
        List<Integer> genes = chromosome.getGenes();
        int index = random.nextInt(genes.size());
        // Replace with a random new integer within the allowed range (example: 0–100)
        genes.set(index, random.nextInt(101));
    }
}
