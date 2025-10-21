package org.ga.crossover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.ga.chromosome.PermutationChromosome;

public class OrderCrossover implements CrossoverStrategy<PermutationChromosome>{
    private final Random random = new Random();

    @Override
    public List<PermutationChromosome> crossover(PermutationChromosome parent1, PermutationChromosome parent2){
        int size = parent1.getGenes().size();

        // choose two random crossover points
        int start = random.nextInt(size);
        int end = random.nextInt(size);

        if(start > end){
            int temp = start;
            start = end;
            end = temp;
        }
        // prepare children
        List<Integer> child1 = new ArrayList<>(Collections.nCopies(size, -1));
        List<Integer> child2 = new ArrayList<>(Collections.nCopies(size, -1));

        // Copy the slice from each parent to its child
        for (int i = start; i < end; i++) {
            child1.set(i,parent1.getGenes().get(i));
            child2.set(i,parent2.getGenes().get(i));
        }
        // Fill remaining genes in order from the other parent
        fillRemainingGenes(child1,parent2.getGenes(),start,end);
        fillRemainingGenes(child2,parent1.getGenes(),start,end);

        // return new children
        return Arrays.asList(
            new PermutationChromosome(child1),
            new PermutationChromosome(child2)
        );
    }


    private void fillRemainingGenes(List<Integer> child,List<Integer> parent,int start,int end){
        int size = parent.size();

        // Start right after the copied slice.
        int current = (end + 1) % size;

        for(int gene : parent){
            if(!child.contains(gene)){
                child.set(current,gene);
                current = (current + 1) % size;
            }
        }
    }
}
