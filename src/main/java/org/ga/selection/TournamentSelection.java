package org.ga.selection;

import java.util.*;
import org.ga.chromosome.*;

public class TournamentSelection<TGene,TChromosome extends Chromosome<TGene>> implements ISelectionStrategy<TGene,TChromosome>{
    private final Random random = new Random();
    private final int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public TChromosome select(List<TChromosome> population) {
        List<TChromosome> tournament = new ArrayList<>();
        for (int i=0;i<tournamentSize;i++){
            // Randomly select an individual
            TChromosome individual = population.get(random.nextInt(population.size()));
            tournament.add(individual);
        }
        // Return the best individual from the tournament
        return Collections.max(tournament,Comparator.comparingDouble(Chromosome::getFitness));
    }
}