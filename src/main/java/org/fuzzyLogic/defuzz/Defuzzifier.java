package org.fuzzyLogic.defuzz;

import java.util.Map;

public interface Defuzzifier {
    double defuzzify(Map<Double, Double> aggregatedSurface);
}
