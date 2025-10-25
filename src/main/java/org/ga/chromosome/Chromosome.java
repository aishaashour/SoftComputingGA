package org.ga.chromosome;
import java.util.List;

public abstract class Chromosome<TGene> implements  Comparable<Chromosome<TGene>>, Cloneable {

    public abstract double getFitness();
    public abstract void setFitness(double fitness);
    @Override
    public abstract Chromosome<TGene> clone();
    public abstract List<TGene> getGenes();
    @Override
    public abstract int compareTo(Chromosome<TGene> other);
}




