package org.neuralNetwork;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;

public class LossPlotter {

    public static void plotLoss(List<Double> lossHistory) {

        XYSeries series = new XYSeries("Training Loss");

        for (int i = 0; i < lossHistory.size(); i++) {
            series.add(i + 1, lossHistory.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Training Loss Curve",
                "Epoch",
                "Loss",
                dataset
        );

        ChartFrame frame = new ChartFrame("Loss Visualization", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
