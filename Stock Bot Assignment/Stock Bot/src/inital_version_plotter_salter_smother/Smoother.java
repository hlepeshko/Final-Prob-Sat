package inital_version_plotter_salter_smother;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Smoother class to apply a moving average smoothing algorithm to data points.
 */
public class Smoother {

    /**
     * Applies a moving average smoothing algorithm to the y-values of points from a CSV file.
     *
     * @param inputFile  the input CSV file with salted points.
     * @param outputFile the output CSV file with smoothed points.
     * @param windowSize the size of the moving window for averaging.
     */
    public void movingAverageSmoothing(String inputFile, String outputFile, int windowSize) {
        // A queue to store the values in the current window for averaging.
        Queue<Double> window = new LinkedList<>();
        // A variable to keep track of the sum of values in the current window.
        double windowSum = 0;

        try {
            // Create a BufferedReader to read from the input file.
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            // Create a BufferedWriter to write to the output file.
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            // Variable to hold each line from the file.
            String line;
            // Loop through each line in the input file.
            while ((line = br.readLine()) != null) {
                // Split the line into x and y values.
                String[] values = line.split(",");
                // Parse the x-value from the string.
                double x = Double.parseDouble(values[0]);
                // Parse the y-value from the string.
                double y = Double.parseDouble(values[1]);

                // Add the new y-value to the window and update the sum.
                window.add(y);
                windowSum += y;

                // Check if the window is full.
                if (window.size() > windowSize) {
                    // Remove the oldest value from the window and the sum.
                    windowSum -= window.remove();
                }

                // Calculate the average of the values in the window.
                double average = windowSum / window.size();
                // Write the x-value and the averaged y-value to the output file.
                bw.write(x + "," + average + "\n");
            }

            // Close the BufferedReader and BufferedWriter.
            br.close();
            bw.close();
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs.
            e.printStackTrace();
        }
    }
}
