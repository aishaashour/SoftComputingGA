package fuzzy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Main runner that reads sample inputs and prints inferred charging rates.
 */
public class Controller {
    public static void main(String[] args) {
        FuzzyInferenceSystem fis = new FuzzyInferenceSystem();

        try {
            InputStream is = Controller.class.getResourceAsStream("/input_samples.csv");
            if (is == null) {
                System.out.println("input_samples.csv not found in resources. Please provide inputs.");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String header = br.readLine(); // skip header
            String line;
            System.out.println("battery,temp -> charging_rate (0..100)");
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                double battery = Double.parseDouble(parts[0]);
                double temp = Double.parseDouble(parts[1]);
                FuzzyInput in = new FuzzyInput(battery, temp);
                double out = fis.evaluate(in);
                System.out.printf("%.1f,%.1f -> %.2f%n", battery, temp, out);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error reading samples: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
