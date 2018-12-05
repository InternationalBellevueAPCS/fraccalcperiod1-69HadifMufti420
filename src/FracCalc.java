import java.util.Scanner;
public class FracCalc {
    public static void main(String[] args){
    	Scanner keyboard = new Scanner(System.in);
    	String input = "";
    	while (input.equals("quit") == false){
    	input = keyboard.nextLine();
    	System.out.println(produceAnswer(input));
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    }
    public static String produceAnswer(String input){ 
    	if (input.equals("quit") == false){
	    	int index = input.indexOf(" ") + 1;
	    	String firstWhole = "0";
	    	String firstNumerator = "0";
	    	String firstDenominator = "1";
	    	int firstWholeInt = 0;
	    	int firstNumeratorInt = 0;
	    	int firstDenominatorInt = 1;
	    	String secondWhole = "0";
	    	String secondNumerator = "0";
	    	String secondDenominator = "1";
	    	int secondWholeInt = 0;
	    	int secondNumeratorInt = 0;
	    	int secondDenominatorInt = 1;
	    	int firstTop = 0;
	    	int secondTop = 0;
	    	int calcTop = 0;
	    	int calcBottom = 1;
	    	int bottom = 1;
	   		if (index != -1) {
	   			String first = input.substring(0, index - 1);
	   			if (first.indexOf("_") > 0) {
	   				firstWhole = first.substring(0, first.indexOf("_"));
	   				firstWholeInt = Integer.parseInt(firstWhole);
	   				}else {
	   				if (first.indexOf("/") < 0) {
	   	   				firstWhole = first;
	   	   				firstWholeInt = Integer.parseInt(firstWhole);
	   	   				}
	   			}
	   			if (first.indexOf("/") > 0) {
	   				firstNumerator = first.substring(first.indexOf("_") + 1, first.indexOf("/"));
	   				firstDenominator = first.substring(first.indexOf("/") + 1, first.length());
	   				firstNumeratorInt = Integer.parseInt(firstNumerator);
	   				firstDenominatorInt = Integer.parseInt(firstDenominator);
	   			}
	   			String second = input.substring((index + 2), input.length());
	   			if (second.indexOf("_") > 0) {
	   				secondWhole = second.substring(0, second.indexOf("_"));
	   				secondWholeInt = Integer.parseInt(secondWhole);
	   				}else {
	   				if (second.indexOf("/") < 0) {
	   	   				secondWhole = second;
	   	   				secondWholeInt = Integer.parseInt(secondWhole);
	   	   				}
	   			}
	   			if (second.indexOf("/") > 0) {
	   				secondNumerator = second.substring(second.indexOf("_") + 1, second.indexOf("/"));
	   				secondDenominator = second.substring(second.indexOf("/") + 1, second.length());
	   				secondNumeratorInt = Integer.parseInt(secondNumerator);
	   				secondDenominatorInt = Integer.parseInt(secondDenominator);
	   			}
	   			System.out.println("firstWhole: " + firstWholeInt + ", firstNumerator: " + firstNumeratorInt + ", firstDenominator: " + firstDenominatorInt);
	   			System.out.println ("secondWhole: " + secondWholeInt + ", secondNumerator: " + secondNumeratorInt + ", secondDenominator: " + secondDenominatorInt);
	   			firstTop = (firstWholeInt * firstDenominatorInt + firstNumeratorInt) * secondDenominatorInt;
	   			secondTop = (secondWholeInt * secondDenominatorInt + secondNumeratorInt) * firstDenominatorInt;
	   			bottom = secondDenominatorInt * firstDenominatorInt;
	   			System.out.println(firstTop + "/" + bottom + ", " + secondTop + "/" + bottom);
	   			if (input.charAt(index) == '+') {
	   				calcTop = firstTop + secondTop;
	   				calcBottom = bottom;
	   			}else {
	   				if (input.charAt(index) == '-') {
	   					calcTop = firstTop - secondTop;
	   					calcBottom = bottom;
	   				} else {
	   					if (input.charAt(index) == '*') {
	   						calcTop = firstTop * secondTop;
	   						calcBottom = bottom * bottom;
	   					}else {
	   						if (input.charAt(index) == '/') {
	   							calcTop = firstTop * bottom;
	   							calcBottom = secondTop * bottom;
	   						}
	   					}
	   				}
	   			}
	   		}
	   		int bigTop = calcTop - (wholeNumber(calcTop, calcBottom) * calcBottom);
	   		return wholeNumber(calcTop, calcBottom) + "_" + bigTop / greatestCommonDivisor(bigTop, calcBottom) + "/" + calcBottom / greatestCommonDivisor(bigTop, calcBottom);
    	}else {
    		return "";
    		}
    }
	   			
	   			// TODO: Implement this function to produce the solution to the input
	        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
	        // Checkpoint 2: Return the second operand as a string representing each part.
	        //               Example "4/5 * 1_2/4" returns "secondWhole:1 secondNumerator:2 secondDenominator:4".
	        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
	        //               Example "4/5 * 1_2/4" returns "6/5".
	        //               Note: Answer does not need to be reduced, but it must be correct.
	        // Final project: All answers must be reduced.
	        //               Example "4/5 * 1_2/4" returns "1_1/5".


    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - first integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - first integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
    public static int wholeNumber(int a, int b) {
    if (Math.abs(a) >= b){
    	return (a / b);
    	}else {
    		return 0;
    	}
    }
}