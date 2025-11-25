package org.fuzzyLogic.defuzz;

import java.util.*;

public class MeanOfMaxima implements Defuzzifier {

    @Override
    public double defuzzify(Map<Double, Double> surface) {
        double max = surface.values().stream().max(Double::compareTo).orElse(0.0);

        double sum = 0; int count = 0;
        for (var e : surface.entrySet()) {
            if (e.getValue() == max) {
                sum += e.getKey();
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }
}
