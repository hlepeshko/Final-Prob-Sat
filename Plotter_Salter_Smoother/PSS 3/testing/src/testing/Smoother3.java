package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * Smoother3 class extends ApplicationFrame to create a graph that displays smoothed data.
 * It reads data from a CSV file, applies a smoothing algorithm, and then plots the smoothed data.
 * The class also writes the smoothed data to a new CSV file.
 * 
 * @author Hope Lepeshko
 */
public class Smoother3 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    private XYSeries originalData = new XYSeries("Original Data");
    private XYSeries smoothedData = new XYSeries("Smoothed Data");
    private int minRange = 2;
    private int maxRange = 100;
    private String inputCsvFilePath = "Function_points_vs3_salted_run2.csv";
    private String outputCsvFilePath = "SmoothedData.csv";

    /**
     * Constructor to initialize the Smoother3 with a title.
     *
     * @param title The title of the window in which the chart will be displayed.
     */
    public Smoother3(String title) {
        super(title);
        generateSmoothedChart("Smoothed Graph", applySmoothing(6000));
    }

    /**
     * Generates and displays a line chart in the application frame.
     *
     * @param chartTitle The title of the line chart.
     * @param dataset    The dataset used for creating the chart.
     */
    private void generateSmoothedChart(String chartTitle, XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, "X-Axis", "Y-Axis", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(panel);
    }

    /**
     * Applies a smoothing algorithm to the data series using a rolling average.
     *
     * @param windowSize The size of the rolling window for the average calculation.
     * @return XYDataset The dataset containing the smoothed data.
     */
    private XYDataset applySmoothing(int windowSize) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        readCSV(inputCsvFilePath, 3);
        for (int i = 0; i < smoothedData.getItemCount(); i++) 
        {
            stats.setWindowSize(windowSize);
            stats.addValue(smoothedData.getY(i).intValue());
            double average = stats.getMean();
            smoothedData.update(smoothedData.getX(i), average);
            saveToFile(smoothedData.getX(i).intValue(), (int) average);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(smoothedData);
        return dataset;
    }

    /**
     * Writes data points to a CSV file.
     *
     * @param x The x-coordinate of the data point.
     * @param y The y-coordinate (smoothed value) of the data point.
     */
    private void saveToFile(int x, int y) {
        try (FileWriter writer = new FileWriter(outputCsvFilePath, true)) 
        {
            if (x == minRange) 
            {
                writer.write("X,Y\n");
            }
            writer.write(x + "," + y + "\n");
            writer.flush();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a CSV file and adds it to the specified data series.
     *
     * @param filePath   The path of the CSV file to read.
     * @param seriesType The type of series to which the data is to be added.
     */
    private void readCSV(String filePath, int seriesType) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                if (counter > 0) {
                    String[] values = line.split(",");
                    int x = Integer.parseInt(values[0]);
                    int y = Integer.parseInt(values[1]);
                    if (seriesType == 3) {
                        smoothedData.add(x, y);
                    }
                }
                counter++;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Displays the smoothed chart in the application frame and prints a completion message.
     */
    public void displayChart() 
    {
        System.out.println("Smoothing process complete.");
        pack();
        setVisible(true);
    }

    /**
     * Retrieves the smoothed data series.
     *
     * @return The XYSeries containing the smoothed data points.
     */
    public XYSeries getSmoothedData() 
    {
        return smoothedData;
    }

    /**
     * Gets the minimum range value for data processing.
     *
     * @return The minimum range value.
     */
    public int getMinRange() 
    {
        return minRange;
    }

    /**
     * Sets the minimum range value for data processing.
     *
     * @param minRange The minimum range value to set.
     */
    public void setMinRange(int minRange) 
    {
        this.minRange = minRange;
    }

    /**
     * Gets the maximum range value for data processing.
     *
     * @return The maximum range value.
     */
    public int getMaxRange() 
    {
        return maxRange;
    }

    /**
     * Sets the maximum range value for data processing.
     *
     * @param maxRange The maximum range value to set.
     */
    public void setMaxRange(int maxRange) 
    {
        this.maxRange = maxRange;
    }

    /**
     * Gets the file path of the input CSV file.
     *
     * @return The file path of the input CSV file.
     */
    public String getInputCsvFilePath() 
    {
        return inputCsvFilePath;
    }

    /**
     * Sets the file path for the input CSV file.
     *
     * @param filePath The file path to set for the input CSV file.
     */
    public void setInputCsvFilePath(String filePath) 
    {
        this.inputCsvFilePath = filePath;
    }

    /**
     * Gets the file path of the output CSV file.
     *
     * @return The file path of the output CSV file.
     */
    public String getOutputCsvFilePath() 
    {
        return outputCsvFilePath;
    }

    /**
     * Sets the file path for the output CSV file.
     *
     * @param filePath The file path to set for the output CSV file.
     */
    public void setOutputCsvFilePath(String filePath) 
    {
        this.outputCsvFilePath = filePath;
    }
    
    /**
    * Used Chat GPT to check work and find mistakes
    * 
    */
}
