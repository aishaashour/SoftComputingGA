package org.ga.chromosome;

import java.util.Arrays;
import java.util.List;

public class BinaryChromosome extends Chromosome<Object>{
    private final int[] genes;
    private double fitness;

    public BinaryChromosome(int length){
        this.genes=new int[length];
    }

    public BinaryChromosome(int[] genes){
        this.genes=genes;
    }

    @Override
    public List<Object> getGenes(){
        return Arrays.stream(genes).boxed().map(i -> (Object) i).toList();
    }

    @Override
    public Chromosome clone() {
        return new BinaryChromosome(Arrays.copyOf(this.genes, this.genes.length));
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
