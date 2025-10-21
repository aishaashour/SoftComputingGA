package org.ga.selection;

import java.util.*;
import org.ga.chromosome.Chromosome;
import org.ga.core.*;


public interface SelectionStrategy {
    List<Chromosome> select(Population pop, int numParents, Random rnd);

}
