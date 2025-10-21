package org.ga.core;

import java.util.*;
import org.ga.chromosome.Chromosome;

public class Population {
    private final  List<Chromosome> chromosomes;

    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = new ArrayList<>(chromosomes);
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void sortByFitness() {
        Collections.sort(chromosomes);
    }
}
