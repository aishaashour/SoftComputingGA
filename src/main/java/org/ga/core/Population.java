package org.ga.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ga.chromosome.Chromosome;

public class Population<T> {
    private final  List<Chromosome<T>> chromosomes;

    public Population(List<Chromosome<T>> chromosomes) {
        this.chromosomes = new ArrayList<>(chromosomes);
    }

    public List<Chromosome<T>> getChromosomes() {
        return chromosomes;
    }

    public void sortByFitness() {
        Collections.sort(chromosomes,Comparator.comparingDouble(Chromosome::getFitness));
    }
}
