package org.ga.replacement;

import java.util.*;
import org.ga.chromosome.Chromosome;
import org.ga.core.*;

public interface ReplacementStrategy {
    Population replace(Population current, List<Chromosome> offspring);
    void setParameters(GAParameters params);
}
