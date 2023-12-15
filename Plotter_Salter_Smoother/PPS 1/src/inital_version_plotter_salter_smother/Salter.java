package inital_version_plotter_salter_smother;

import java.io.*;
import java.util.Random;

/**
 * Salter class to add Gaussian noise to data points.
 * 
 * @author Hope Lepeshko
 */
public class Salter 
{

    /**
     * Adds Gaussian noise to the y-values of points in a CSV file.
     *
     * @param inputFile  the input CSV file with original data points.
     * @param outputFile the output CSV file with salted data points.
     * @param mean       the mean value of the Gaussian noise.
     * @param stddev     the standard deviation of the Gaussian noise.
     */
    // public method called addGaussianNoise
	// Think of Gaussian Noise as random noise
	public void addGaussianNoise(String inputFile, String outputFile, double mean, double stddev) 
	{
        // Create a Random object for generating Gaussian noise
        Random random = new Random();

        try {
            // Create a BufferedReader to read from the input file
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            // Create a BufferedWriter to write to the output file
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            // Declare a string to hold each line from the file
            String line;
            // Loop through each line in the input file
            while ((line = br.readLine()) != null) 
            {
                // Split the line into x and y values
                String[] values = line.split(",");
                // Parse the x-value from the string
                double x = Double.parseDouble(values[0]);
                // Parse the y-value from the string
                double y = Double.parseDouble(values[1]);

                // Add Gaussian noise to the y-value
                // The noise is generated based on the specified mean and standard deviation
                double noisyY = y + mean + stddev * random.nextGaussian();
                // Write the x value and the noisy y value to the output file
                bw.write(x + "," + noisyY + "\n");
            }

            // Close the BufferedReader and BufferedWriter
            br.close();
            bw.close();
        } 
        catch (IOException e) 
        {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
        }
        
        // Used Heather and https://github.com/ilindaa/Project-1/blob/main/Project1/src/GraphProgram/Plotter%20Salter%20Smoother%20Graph%20Results/P%26S%20Graph%20Program%20-%20Plotter%2C%20Salter%2C%20Smoother%20Graph%20Results%20(2%20runs).docx
        // as references
    }
}
