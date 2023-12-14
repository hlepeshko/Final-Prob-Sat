/**
 * TestStatsLibrary2 class used to test and print
 * the statistical methods implemented in the ChapterFour class and the ChapterFive class
 * 
 * @author Hope Lepeshko
 */

public class TestStatsLibrary2 
{

    /**
     * The main method serves as the entry point for the program. 
     * It tests various methods of the ChapterFour class related to 
     * continuous and discrete uniform distributions.
     *
     * @param args Command line arguments (not used in this program).
     */
	public static void main(String[] args) 
    {
        // Testing continuous uniform distribution form chapter four
        System.out.println("Continuous Uniform Distribution:");
        // Interval for continuous distribution
        double a = 0, b = 5; 
        // A point within the interval
        double xContinuous = 2.5; 
        System.out.println("PDF at " + xContinuous + ": " + ChapterFour.continuousPDF(xContinuous, a, b));
        System.out.println("CDF at " + xContinuous + ": " + ChapterFour.continuousCDF(xContinuous, a, b));
        System.out.println("Random number: " + ChapterFour.generateContinuousRandom(a, b));

        // Testing discrete uniform distribution from chapter four
        System.out.println("\nDiscrete Uniform Distribution:");
        // Number of events for discrete distribution
        int n = 10; 
        // A specific event
        int xDiscrete = 5; 
        System.out.println("PDF: " + ChapterFour.discretePDF(n));
        System.out.println("CDF at " + xDiscrete + ": " + ChapterFour.discreteCDF(xDiscrete, n));
        System.out.println("Random number: " + ChapterFour.generateDiscreteRandom(n));
        
        // Testing conditional probability from ChapterFive
        System.out.println("\nConditional Probability:");
        // Hypothetical probability of event A
        double probabilityA = 0.3; 
        // Hypothetical probability of both A and B occurring
        double probabilityAandB = 0.1; 
        System.out.println("Conditional probability of B given A: " + ChapterFive.conditionalProbability(probabilityA, probabilityAandB));
    }
}
