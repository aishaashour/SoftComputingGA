package org.ga.selection;

import java.util.*;
import org.ga.chromosome.*;

public class TournamentSelection<T extends Chromosome> implements SelectionStrategy<T>{
    private final Random random = new Random();
    private final int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public T select(List<T> population) {
        List<T> tournament = new ArrayList<>();
        for (int i=0;i<tournamentSize;i++){
            // Randomly select an individual
            T individual = population.get(random.nextInt(population.size()));
            tournament.add(individual);
        }
        // Return the best individual from the tournament
        return Collections.max(tournament,Comparator.comparingDouble(Chromosome::getFitness));
    }
}