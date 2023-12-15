package inital_version_plotter_salter_smother;

/**
 * PSS1Test class to demonstrate the usage of Plotter, Salter, and Smoother classes.
 */
public class PSS1Test {
    public static void main(String[] args) {
        // Creating instances of Plotter, Salter, and Smoother classes.
        Plotter plotter = new Plotter();
        Salter salter = new Salter();
        Smoother smoother = new Smoother();

        // File names for the data at each stage: original, salted, and smoothed.
        String originalDataFile = "function_points.csv";
        String saltedDataFile = "function_points_salted.csv";
        String smoothedDataFile = "function_points_smoothed.csv";

        // Step 1: Plot an exponential function using the Plotter class.
        // Parameters: Start x-value = 0, End x-value = 10, Number of points = 100, Coefficient a = 1, Base b = 1.5.
        plotter.plotExponentialFunction(0, 10, 100, 1, 1.5);
        // Print to console indicating completion of plotting.
        System.out.println("Exponential function plotted.");

        // Step 2: Add Gaussian noise to the plotted data using the Salter class.
        // Parameters: Input file, Output file, Mean of noise = 0.0, Standard deviation of noise = 1.0.
        salter.addGaussianNoise(originalDataFile, saltedDataFile, 0.0, 1.0);
        // Print to console indicating completion of adding noise.
        System.out.println("Gaussian noise added.");

        // Step 3: Apply smoothing to the salted data using the Smoother class.
        // Parameters: Input file, Output file, Window size for moving average = 5.
        smoother.movingAverageSmoothing(saltedDataFile, smoothedDataFile, 5);
        // Print to console indicating completion of smoothing.
        System.out.println("Data smoothed.");
    }
}
