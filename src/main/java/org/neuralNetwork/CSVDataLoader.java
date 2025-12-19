package org.neuralNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataLoader {

    public static class Dataset {
        public double[][] x;
        public double[][] y;

        public Dataset(double[][] x, double[][] y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Dataset load(String path) {

        List<double[]> features = new ArrayList<>();
        List<double[]> labels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(",");

                double[] x = new double[4];
                for (int i = 0; i < 4; i++) {
                    x[i] = Double.parseDouble(tokens[i]);
                }

                double[] y = { Double.parseDouble(tokens[4]) };

                features.add(x);
                labels.add(y);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Dataset(
                features.toArray(new double[0][]),
                labels.toArray(new double[0][])
        );
    }
}
