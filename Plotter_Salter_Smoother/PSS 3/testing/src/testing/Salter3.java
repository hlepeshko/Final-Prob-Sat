package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * Salter3 class extends ApplicationFrame to process and display a graph.
 * It reads data from a CSV file, applies a random "salt" to the y-values of the data,
 * and then generates a new graph with the altered data.
 * This class is designed to work with functions such as y = 6 * 2^x.
 * 
 * @author Hope Lepeshko
 */
public class Salter3 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    private XYSeries originalSeries = new XYSeries("Original Data");
    private XYSeries saltedSeries = new XYSeries("Salted Data");
    private static final Random randomGenerator = new Random();
    private int minLimit = 2;
    private int maxLimit = 100;

    /**
     * Constructor for Salter3.
     * Initializes the chart with a given title and prepares the salted chart for display.
     *
     * @param title The title of the graph.
     */
    public Salter3(String title) {
        super(title);
        generateSaltedChart("Salted Graph", applySalting(2500, 7500));
    }

    /**
     * Generates a chart using JFreeChart and displays it in the application frame.
     *
     * @param title The title of the chart.
     * @param dataset The dataset used to create the chart.
     */
    private void generateSaltedChart(String title, XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(title, "X", "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(panel);
    }

    /**
     * Applies random "salting" to the y-values of the data series.
     *
     * @param minSalt The minimum value for the salt.
     * @param maxSalt The maximum value for the salt.
     * @return XYDataset A dataset containing the salted data.
     */
    private XYDataset applySalting(int minSalt, int maxSalt) {
        readCSV("Function_points_vs3_run2.csv");
        for (int i = 0; i < originalSeries.getItemCount(); i++) {
            int randomValue = randomGenerator.nextInt(maxSalt - minSalt) + minSalt;
            int newY = originalSeries.getY(i).intValue() + randomValue;
            saltedSeries.add(originalSeries.getX(i), newY);
            writeToFile(originalSeries.getX(i).intValue(), newY);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(saltedSeries);
        return dataset;
    }

    /**
     * Writes the salted data points to a CSV file.
     *
     * @param x The x-value of the data point.
     * @param y The y-value (altered) of the data point.
     */
    private void writeToFile(int x, int y) {
        try (FileWriter writer = new FileWriter("SaltedData.csv", true)) {
            if (x == minLimit) {
                writer.write("X,Y\n");
            }
            writer.write(x + "," + y + "\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a CSV file and loads it into the originalSeries.
     *
     * @param filePath The path to the CSV file.
     */
    private void readCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    int x = Integer.parseInt(values[0]);
                    int y = Integer.parseInt(values[1]);
                    originalSeries.add(x, y);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the salted data chart.
     */
    public void displayChart() {
        System.out.println("Salted data is ready.");
        pack();
        setVisible(true);
    }

    /**
     * Retrieves the salted data series.
     *
     * @return The XYSeries containing the salted data points.
     */
    public XYSeries getSaltedSeries() {
        return saltedSeries;
    }  
    
    /**
    * Used Chat GPT to check work and find mistakes
    * 
    */
}
