package org.ga.chromosome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationChromosome extends Chromosome<Integer>{
    // 
    private final List<Integer>genes;
    
    public PermutationChromosome(List<Integer> genes) {
        this.genes=new ArrayList<>(genes);
    }
    public static PermutationChromosome random(int size) {
        List<Integer> genes = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            genes.add(i);
        }
        Collections.shuffle(genes);
        return new PermutationChromosome(genes);
    }
    
    @Override
    public List<Integer> getGenes(){
        return genes;
    }

    private double fitness = 0.0;

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }


    @Override 
    public PermutationChromosome clone(){
        PermutationChromosome clone = new PermutationChromosome(new ArrayList<>(this.genes));
        clone.setFitness(this.fitness);
        return clone;
    }

     @Override
    public int compareTo(Chromosome<Integer> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }
}