package inital_version_plotter_salter_smother;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Plotter class to plot an exponential function
 * 
 * @author Hope Lepeshko
 * 
 */
public class Plotter 
{

    /**
     * Plots an exponential function and writes the data points to a CSV file.
     *
     * @param start     the starting x-value of the plot.
     * @param end       the ending x-value of the plot.
     * @param numPoints the number of data points to generate.
     * @param a         the coefficient of the function.
     * @param b         the base of the exponential function.
     */
    public void plotExponentialFunction(int start, int end, int numPoints, double a, double b) 
    {
        try 
        {
            // Create a new File object for the output CSV file
            File file = new File("function_points.csv");
            // Create a FileWriter to write to the file
            FileWriter fw = new FileWriter(file);
            // Wrap FileWriter with BufferedWriter for efficient writing
            BufferedWriter bw = new BufferedWriter(fw);

            // Calculate the interval between x values
            // interval is determined by (subtracting start from end) and dividing that by (# of points minus 1)
            double interval = (double) (end - start) / (numPoints - 1);

            // For loop through each point to compute and write the data
            for (int i = 0; i < numPoints; i++) {
                // Calculate the x value for this point
                double x = start + i * interval;
                // Calculate the y value using the exponential function
                double y = a * Math.pow(b, x);
                // Write the x and y values to the CSV file
                bw.write(x + "," + y + "\n");
         }

            // Close the BufferedWriter and FileWriter
            bw.close();
            fw.close();
        } 
        catch (IOException e) 
        {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
    
    // Used Heather and https://github.com/ilindaa/Project-1/blob/main/Project1/src/GraphProgram/Plotter%20Salter%20Smoother%20Graph%20Results/P%26S%20Graph%20Program%20-%20Plotter%2C%20Salter%2C%20Smoother%20Graph%20Results%20(2%20runs).docx
    // as references
}
