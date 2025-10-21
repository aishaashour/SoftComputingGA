// This file does not contain any Markdown code fence markers.
package org.ga.chromosome;

import java.util.Arrays;
import java.util.List;

public class IntegerChromosome extends Chromosome<Integer>{
    private final int[] genes;
    private double fitness;

    public IntegerChromosome(int length){
        this.genes=new int[length];
    }

    public IntegerChromosome(int[] genes){
        this.genes=genes;
    }
    @Override
    public List<Integer> getGenes(){
        return Arrays.stream(genes).boxed().toList();
    }

    @Override
    public Chromosome<Integer> clone() {
        return new IntegerChromosome(Arrays.copyOf(this.genes, this.genes.length));
    
    }
    

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    // @Override
    public void getFitness(double fitness) {
        // deprecated signature - no-op
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Chromosome o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }

}
