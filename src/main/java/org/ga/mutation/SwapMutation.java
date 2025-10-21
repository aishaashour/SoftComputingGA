package org.ga.mutation;
import java.util.*;
import org.ga.chromosome.*;

public class SwapMutation implements MutationStrategy<PermutationChromosome>{
    private final Random random = new Random();

    @Override
    public void mutate(PermutationChromosome chromosome){
        List<Integer> genes = chromosome.getGenes();
        int size = genes.size();

        int i = random.nextInt(size);
        int j = random.nextInt(size);
        Collections.swap(genes, i, j);
    }
}