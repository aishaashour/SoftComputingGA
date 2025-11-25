package org.fuzzyLogic.defuzz;

import java.util.Map;

public class CentroidDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(Map<Double, Double> surface) {
        double num = 0, den = 0;
        for (var e : surface.entrySet()) {
            num += e.getKey() * e.getValue();
            den += e.getValue();
        }
        return den == 0 ? 0 : num / den;
    }
}
