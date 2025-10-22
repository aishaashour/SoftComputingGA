package org.ga.chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoubleChromosome extends Chromosome<Double> {
    private final List<Double> genes;
    private double fitness;
    private final Random random = new Random();

    // Constructor 1: random initialization
    public DoubleChromosome(int size) {
        genes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // Generate random doubles in [0, 1)
            genes.add(random.nextDouble());
        }
    }

    // Constructor 2: from existing list
    public DoubleChromosome(List<Double> genes) {
        this.genes = genes;
    }

    @Override
    public List<Double> getGenes() {
        return genes;
    }

    @Override
    public Chromosome<Double> clone() {
        DoubleChromosome clone = new DoubleChromosome(new ArrayList<>(this.genes));
        clone.setFitness(this.fitness);
        return clone;
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
    public int compareTo(Chromosome<Double> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }
}
