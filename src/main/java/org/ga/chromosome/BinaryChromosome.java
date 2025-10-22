package org.ga.chromosome;

import java.util.Arrays;
import java.util.List;

public class BinaryChromosome extends Chromosome<Boolean>{
    private final Boolean[] genes;
    private double fitness;

    public BinaryChromosome(int length){
        this.genes=new Boolean[length];
    }

    public BinaryChromosome(Boolean[] genes){
        this.genes=genes;
    }

    @Override
    public List<Boolean> getGenes(){
        return Arrays.stream(genes).toList();
    }

    @Override
    public BinaryChromosome clone() {
        return new BinaryChromosome(Arrays.copyOf(this.genes, this.genes.length));
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
   

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Chromosome<Boolean> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }



}
