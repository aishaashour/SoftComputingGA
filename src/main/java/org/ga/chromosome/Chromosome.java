package org.ga.chromosome;
import java.util.List;

public abstract class Chromosome<T> implements  Comparable<Chromosome>, Cloneable {
    // private double fitness;

    public abstract double getFitness();
    public abstract void setFitness(double fitness);
    @Override
    public abstract Chromosome clone();
    public abstract List<T> getGenes();
    // @Override
    // public abstract int compareTo(Chromosome other);
}




