/**
 * The ChapterFour class has methods for calculating the probability density function
 * and cumulative distribution function for continuous and discrete uniform distributions.
 * It also includes methods for generating random numbers following these distributions.
 * 
 * @author Hope Lepeshko
 */
public class ChapterFour 
{

    /**
     * Calculates the PDF of a continuous uniform distribution.
     * 
     * @param x The point at which to evaluate the PDF.
     * @param a The lower bound of the distribution interval.
     * @param b The upper bound of the distribution interval.
     * @return The PDF value at point x.
     */
    // Method declaration of public static method which returns type double
	// Method name continuousPDF for continuous probability density function
	// Method take three parameters
	public static double continuousPDF(double x, double a, double b) 
    {
        // If statement checks if x is outside the interval [a, b]
		// If x is less than a or x is greater than b 
		if (x < a || x > b) 
        {
            // Return statement of 0 because if x is outside the interval the continuous
			// uniform distribution probability is zero
			return 0;
        }
        // If x is inside the interval return 1 divided by b minus a
		return 1.0 / (b - a);
    }

    /**
     * Calculates the CDF of a continuous uniform distribution.
     * 
     * @param x The point at which to evaluate the CDF.
     * @param a The lower bound of the distribution interval.
     * @param b The upper bound of the distribution interval.
     * @return The CDF value at point x.
     */
	// Method declaration of public static method which returns type double
	// Method name continuousCDF for continuous cumulative distribution function
	// Method takes three parameters
	public static double continuousCDF(double x, double a, double b) 
    {
        // If statement checks if x is less than a, a being the lower bound
		if (x < a) 
        {
            // If x is less than the lower bound return zero, because if x is less
			// than the lower bound in a cumulative distribution the probability is zero
			return 0;
        } 
        // Else if statement checks if x is greater than b, b being the upper bound
		else if (x > b) 
        {
			// If x is greater than the upper bound return one, because if x is greater
			// than the upper bound in a cumulative distribution the probability is 100%
			return 1;
        } 
        // Else statement for when x is within the interval of [a, b]
		else 
        {
            // return statement returns the proportion of the interval from a to x relative to 
			// [a, b]
			// returns (x minus a) divided by (b minus a)
			return (x - a) / (b - a);
        }
    }

    /**
     * Calculates the PDF of a discrete uniform distribution.
     * 
     * @param n The number of equally likely events.
     * @return The PDF value, which is the same for each event.
     */
	// Method declaration of public static method which returns type double
	// Method name discretePRF for discrete probability density function
	// Method takes one parameter
	public static double discretePDF(int n) 
    {
        // return statement returns one divided by n
		// All outcomes in a discrete uniform distribution are equally likey so we can use 1
		// as the PDF and divide that by the number of events, n
		return 1.0 / n;
    }

    /**
     * Calculates the CDF of a discrete uniform distribution.
     * 
     * @param x The event number up to which the CDF is calculated.
     * @param n The total number of events.
     * @return The CDF value at the given event number x.
     */
	// Method declaration of public static method which returns type double
	// Method name discreteCDF for discrete cumulative distribution function
	// Method takes two parameters
    public static double discreteCDF(int x, int n) 
    {
        // If statement checks if x is less than one
    	// Checking if input, x is outside the lower bound of the distribution
    	if (x < 1) 
        {
            // Return statement returns zero because the cumulative probability
    		// of an event number less than 1 is zero
    		return 0;
        } 
        // else if statement checks if x is greater than n, n being the total number of events
    	else if (x > n) 
        {
            // Return statement returns one because if the event number is 
    		// greater than the total number of events, the cumulative probability is 100%
    		return 1;
        } 
        // Else statement for when x is within the range form one to n
    	else 
        {
            // Return statement returns x divided by n which is the cumulative probability of the events,
    		// n up to and including x
    		// we have to cast x to double to make sure the division is in decimal form
    		return (double) x / n;
        }
    }

    /**
     * Generates a random number following a continuous uniform distribution within the given interval.
     * 
     * @param a The lower bound of the distribution interval.
     * @param b The upper bound of the distribution interval.
     * @return A random number within the interval [a, b].
     */
	// Method declaration of public static method which returns type double
	// Method name generateContinuousRandom to generate a random number that is inside a specified range
	// Method takes two parameters
    public static double generateContinuousRandom(double a, double b) 
    {
        // Return statement a plus random number multiplied by (b minus a)
    	// Math.randon() generates a random double greater than or equal to 0.o and less than or equal to 1.0
    	// (b - a) calculates the range of the interval
    	// multiplying Math.random() and (b - a) scales the random number, meaning the random number is now 
    	// greater than or equal to 0.0 and less than or equal to (b - a)
    	// Finally we add a to change the random number to start form a instead of 0.0
    	// Now the range is [a, b)
    	return a + Math.random() * (b - a);
    }

    /**
     * Generates a random number following a discrete uniform distribution for the given number of events.
     * 
     * @param n The number of events.
     * @return A random event number between 1 and n, inclusive.
     */
	// Method declaration of public static method which returns type double
	// Method name generateDiscreteRandom to generate a random number that is inside a specified range
	// Method takes one parameter
    public static int generateDiscreteRandom(int n) 
    {
        // Return statement (random number multiplied by n) plus one
    	// Math.randon() generates a random double greater than or equal to 0.o and less than or equal to 1.0
    	// (Math.random() * n) multiply the random number by n to scale the range [0, n)
    	// Now the random number is a double that is greater than or equal to 0.0 and less than n
    	// (int) is a casting operation to convert the double to an integer to get rid of the decimal
    	// finally add one to shift the range from [0, n - 1] to [1, n]
    	return (int) (Math.random() * n) + 1;
    }
    // Used Chat GPT to check my work
    // Used this reference https://stackoverflow.com/questions/9242907/how-do-i-generate-normal-cumulative-distribution-in-java-its-inverse-cdf-how
    // Honestly not sure if any of this is the correct way to calculate PDF or CDF, please have mercy on me

}

