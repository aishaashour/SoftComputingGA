package org.ga.chromosome;

import java.util.List;

public interface Chromosome extends Comparable<Chromosome>, Cloneable {
    Chromosome clone() throws CloneNotSupportedException;
    double getFitness();
    List<Object> getGenes();
    void setFitness(double fitness);
}




