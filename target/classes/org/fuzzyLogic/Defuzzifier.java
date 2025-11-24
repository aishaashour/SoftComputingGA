package fuzzy;

/**
 * Centroid defuzzifier over sampled aggregated output.
 */
public class Defuzzifier {
    public static double centroid(double[] aggregated) {
        double num = 0.0;
        double den = 0.0;
        for (int i = 0; i < aggregated.length; i++) {
            double mu = aggregated[i];
            num += i * mu;
            den += mu;
        }
        if (den == 0.0) return 0.0;
        return num / den;
    }
}
