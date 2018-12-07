import java.util.Scanner;
public class FracCalc {
    public static void main(String[] args){
    	Scanner keyboard = new Scanner(System.in);
    	String input = "";
    	while (input.equals("quit") == false){
    	input = keyboard.nextLine();
    	System.out.println(produceAnswer(input));
    }
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
	   			//assigns the first side of the equation to a string
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
	   			//assigns each component of the first string to a variable
	   			if (first.indexOf("/") > 0) {
	   				firstNumerator = first.substring(first.indexOf("_") + 1, first.indexOf("/"));
	   				firstDenominator = first.substring(first.indexOf("/") + 1, first.length());
	   				firstNumeratorInt = Integer.parseInt(firstNumerator);
	   				firstDenominatorInt = Integer.parseInt(firstDenominator);
	   				if (firstWholeInt < 0) {
	   					firstNumeratorInt *= -1;
	   				}
	   			}
	   		//assigns the second side of the equation to a string
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
	   			//assigns each component of the second string to a variable
	   			if (second.indexOf("/") > 0) {
	   				secondNumerator = second.substring(second.indexOf("_") + 1, second.indexOf("/"));
	   				secondDenominator = second.substring(second.indexOf("/") + 1, second.length());
	   				secondNumeratorInt = Integer.parseInt(secondNumerator);
	   				secondDenominatorInt = Integer.parseInt(secondDenominator);
	   				if (secondWholeInt < 0) {
	   					secondNumeratorInt *= -1;
	   				}
	   			}
	   			//gets all of the values into two fractions with the same denominator for easy calculation
	   			firstTop = (firstWholeInt * firstDenominatorInt + firstNumeratorInt) * secondDenominatorInt;
	   			secondTop = (secondWholeInt * secondDenominatorInt + secondNumeratorInt) * firstDenominatorInt;
	   			bottom = secondDenominatorInt * firstDenominatorInt;
	   			
	   			//the following section determines the operator used and takes care of calculations
	   			if (input.charAt(index) == '+') {
	   				calcTop = firstTop + secondTop;
	   				calcBottom = bottom;
	   				//addition calculation
	   			}else {
	   				if (input.charAt(index) == '-') {
	   					calcTop = firstTop - secondTop;
	   					calcBottom = bottom;
	   					//subtraction calculation
	   				} else {
	   					if (input.charAt(index) == '*') {
	   						calcTop = firstTop * secondTop;
	   						calcBottom = bottom * bottom;
	   						//multiplication calculation
	   					}else {
	   						if (input.charAt(index) == '/') {
	   							calcTop = firstTop * bottom;
	   							calcBottom = secondTop * bottom;
	   							//cross multiplies for division
	   							if (calcBottom < 0) {
	   								calcBottom *= -1;
	   								calcTop *= -1;
	   								//makes the numerator negative instead of the denominator
	   							}
	   						}
	   					}
	   				}
	   			}
	   		}
	   		int factoredTop = calcTop - (wholeNumber(calcTop, calcBottom) * calcBottom);
	   		//simplifies out the whole number
	   		if (factoredTop == 0){
	   			return "" + wholeNumber(calcTop, calcBottom);
	   			//if there's no fraction, only prints whole number
	   			}else {
	   			if (wholeNumber(calcTop, calcBottom) == 0) {
			   		return "" + factoredTop / greatestCommonDivisor(factoredTop, calcBottom) + "/" + Math.abs(calcBottom / greatestCommonDivisor(factoredTop, calcBottom));
			   		//if  there's no whole number, only prints fraction
	   			}else {
		   			return wholeNumber(calcTop, calcBottom) + "_" + Math.abs(factoredTop / greatestCommonDivisor(factoredTop, calcBottom)) + "/" + Math.abs(calcBottom / greatestCommonDivisor(factoredTop, calcBottom));
		   			//prints both whole number and fraction if both are present
	   			}
		   	}
	   		}else {
    		return "";
    		//returns an empty string if the user enters the equation without spaces so that it doesn't error
	   		}
    	}
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
    public static int wholeNumber(int a, int b) {
    	//figures out the highest whole number that can be factored out of the fraction
    	if (Math.abs(a) >= b){
	    	return (a / b);
	    	}else {
	    		return 0;
	    	}
	    }
	}