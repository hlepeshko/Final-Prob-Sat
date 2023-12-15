package testing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.io.FileWriter;

/**
 * Plotter3 class extends ApplicationFrame to create a graphical representation of a mathematical function.
 * It plots the function y = 6 * 2^x, and outputs the data points to a CSV file.
 * 
 * @author Hope Lepeshko
 */
public class Plotter3 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    private XYSeries series;
    private int minLimit;
    private int maxLimit;

    /**
     * Constructor to initialize the Plotter3 with a title.
     * @param title The title of the graph window.
     */
    public Plotter3(String title) {
        super(title);
        series = new XYSeries("Graph Data");
        minLimit = 2;
        maxLimit = 100;
        generateChart("Graphical Representation", createDataset());
    }

    /**
     * Displays the chart in a window.
     */
    public void display() {
        System.out.println("Graph generation complete");
        pack();
        setVisible(true);
    }

    /**
     * Generates a line chart using JFreeChart library.
     * @param chartTitle The title of the chart.
     * @param dataset The dataset used to create the chart.
     */
    private void generateChart(String chartTitle, XYDataset dataset) {
        JFreeChart lineChart = ChartFactory.createXYLineChart(chartTitle, "X-Axis", "Y-Axis", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    /**
     * Creates the dataset for the chart by plotting the mathematical function.
     * @return XYDataset The dataset for the chart.
     */
    private XYDataset createDataset() {
        for (int i = minLimit; i <= maxLimit; i++) {
            int result = (int) (6 * Math.pow(2, i));
            series.add(i, result);
            writeToCSV(i, result);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    /**
     * Writes the calculated data points to a CSV file.
     * @param x The x-value of the data point.
     * @param y The y-value (function output) of the data point.
     */
    private void writeToCSV(int x, int y) {
        try (FileWriter writer = new FileWriter("GraphData.csv", true)) {
            if (x == minLimit) {
                writer.write("X,Y\n");
            }
            String line = x + "," + y + "\n";
            writer.write(line);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the series of data points generated for the chart.
     * @return XYSeries The series of data points.
     */
    public XYSeries getSeries() {
        return series;
    }

    /**
     * Gets the minimum limit for x-values in the dataset.
     * @return int The minimum x-value.
     */
    public int getMinLimit() {
        return minLimit;
    }

    /**
     * Gets the maximum limit for x-values in the dataset.
     * @return int The maximum x-value.
     */
    public int getMaxLimit() {
        return maxLimit;
    }
    
    /**
     * Used Chat GPT to check work and find misakes
     * 
     */
}