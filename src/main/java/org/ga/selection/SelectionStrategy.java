package org.ga.selection;

import java.util.*;
import org.ga.chromosome.Chromosome;

public interface SelectionStrategy<T extends Chromosome> {
    T select(List<T> population);
}
