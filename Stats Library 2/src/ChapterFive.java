/**
 * ChapterFive class includes methods for calculating conditional probabilities.
 */
public class ChapterFive 
{

    /**
     * Calculates the conditional probability of B given A.
     *
     * @param probabilityA Probability of event A.
     * @param probabilityAandB Probability of both A and B occurring.
     * @return The conditional probability of B given A.
     */
    // Method declaration of public static method which returns type double
    // Method name conditionalProbability for calculating conditional probability
    // Method takes two parameters
	public static double conditionalProbability(double probabilityA, double probabilityAandB) 
	{
        // If statement checks if probabilityA is zero
        // Checking to prevent division by zero, becuase the conditional probability formula requires division by P(A)
		if (probabilityA == 0) 
        {
            // Throw an IllegalArgumentException if probabilityA is zero
            // Making sure that the method does not continue with an invalid division
			throw new IllegalArgumentException("Probability of A must be greater than 0");
        }
	    // Return statement returns the conditional probability
        // P(B|A) is calculated as P(A and B) divided by P(A), which is formula for conditional probability
		return probabilityAandB / probabilityA;
    }
	// Used Chat GPT to check my work
	// Again not sure if this is correct, sorry :(
}
