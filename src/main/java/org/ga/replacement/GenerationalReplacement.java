package org.ga.replacement;

import java.util.*;
import org.ga.chromosome.*;
import org.ga.core.*;



public class GenerationalReplacement implements ReplacementStrategy {
    @Override
    public Population replace(Population current, List<Chromosome> offspring) {
        return new Population(offspring);
    }

    @Override
    public void setParameters(GAParameters params) {
        // No parameters needed for generational replacement
    }
}

