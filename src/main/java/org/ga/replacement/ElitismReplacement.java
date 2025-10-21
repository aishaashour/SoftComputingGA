package org.ga.replacement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.ga.chromosome.Chromosome;

public class ElitismReplacement<T extends Chromosome> implements ReplacementStrategy<T>{
    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    @Override
    public List<T> replace(List<T> oldPop,List<T>newPop){
        List<T>combined = new ArrayList<>(newPop);

        oldPop.sort(Comparator.comparingDouble((Chromosome c) -> c.getFitness()).reversed());
        for (int i = 0; i < eliteCount && i<oldPop.size(); i++) {
            combined.set(i, (T)oldPop.get(i).clone());
        }
        return combined;
    }


}